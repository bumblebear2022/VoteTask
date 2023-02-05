package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.domain.SavedVote;
import by.itacademy.jd2.votetask.service.api.*;
import by.itacademy.jd2.votetask.service.factories.GenreServiceSingleton;
import by.itacademy.jd2.votetask.service.factories.PerformerServiceSingleton;
import by.itacademy.jd2.votetask.service.factories.VoteServiceSingleton;
import by.itacademy.jd2.votetask.util.MailDataSourceHolder;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AutoMailService implements IMailService, IAutoMailService {

    public static final String SMTP_SERVER = "smtp.mail.ru";
    public static final String SMTP_PORT = "465";
    public static final String SUBJECT = "Group 2 voting app";

    public final String MAIL_LOGIN;

    public final String MAIL_PASSWORD;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final IPerformerService performerService = PerformerServiceSingleton.getInstance();

    private final IGenreService genreService = GenreServiceSingleton.getInstance();

    private final IVoteService voteService = VoteServiceSingleton.getInstance();

    private final ScheduledThreadPoolExecutor threadPoolExecutor;

    private boolean stopAutoSending = false;

    public AutoMailService() {
        MAIL_LOGIN = MailDataSourceHolder.getMailData().get("username");
        MAIL_PASSWORD = MailDataSourceHolder.getMailData().get("password");
        threadPoolExecutor = new ScheduledThreadPoolExecutor(4);
        //new Thread(this::startSendingMail).start();
    }

    private void startSendingMail() {
        ConcurrentLinkedDeque<Future<Boolean>> listOfThreadTasks = new ConcurrentLinkedDeque<>() ;
        while (!stopAutoSending) {
            List<SavedVote> unsentMails = voteService.readUnsentVotes();
            if (!unsentMails.isEmpty()) {
                for (SavedVote vote : unsentMails) {
                    listOfThreadTasks.add(threadPoolExecutor.submit(() -> sendAndCheck(vote)));
                }
                boolean isCompleted;
                do {
                    isCompleted = true;
                    for (Future<Boolean> taskBuffer : listOfThreadTasks) {
                        if (!taskBuffer.isDone()) {
                            isCompleted = false;
                        }
                    }
                    if (!isCompleted) {
                        try {
                            TimeUnit.MINUTES.sleep(1);
                        } catch (InterruptedException ignored) {
                        }
                    }
                } while (isCompleted);
            } else {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    public boolean sendAndCheck(SavedVote savedVote) {
        if (performSending(savedVote)) {
            voteService.updateSendingInfo(savedVote.getId(), true);
            return true;
        } else {
            voteService.updateSendingInfo(savedVote.getId(), false);
            return false;
        }
    }

    private boolean performSending(SavedVote savedVote) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_SERVER);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", SMTP_PORT);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, getAuthenticator());

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MAIL_LOGIN));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(savedVote.getEmail()));

            message.setSubject(SUBJECT);

            String text = parseTextFromVote(savedVote);
            message.setText(text);

            //Transport.send(message);
            throw new MessagingException();
        } catch (MessagingException e) {
            return false;
        }
        //return true;
    }

    private String parseTextFromVote(SavedVote savedVote) {
        LocalDateTime createDateTime = savedVote.getCreateDateTime();
        String voteTime = createDateTime.format(format);

        Long voiceForPerformer = savedVote.getVoiceForPerformer().getId();
        List<Performer> performerList = performerService.getContent();
        String performer = performerList.stream()
                .filter(Performer -> Performer.getId().equals(voiceForPerformer))
                .map(Performer::getNickName)
                .findAny().orElse("");


        String joinedGenres = getVotedGenres(savedVote);

        String about = savedVote.getAbout();

        StringBuffer emailText = new StringBuffer("Hi!\nToday in ");
        emailText.append(voteTime);
        emailText.append(" you voted for Performer: ");
        emailText.append(performer);
        emailText.append(" and Genres: ");
        emailText.append(joinedGenres);
        emailText.append("\nYour about section: ");
        emailText.append(about);

        return emailText.toString();
    }

    private String getVotedGenres(SavedVote vote) {
        List<Genre> genresList = genreService.getContent();
        Map<Long, Genre> genresMap = genresList.stream()
                .collect(Collectors.toMap(Genre::getId, genre -> genre));

        List<Long> voicesForGenres = new ArrayList<>();
        for (Genre genre: vote.getVoicesForGenres()) {
            voicesForGenres.add(genre.getId());
        }
        List<String> votedGenresList = voicesForGenres.stream()
                .map(genresMap::get)
                .map(Genre::getTitle)
                .collect(Collectors.toList());

        return String.join(", ", votedGenresList);
    }

    private Authenticator getAuthenticator() {
        String username = MAIL_LOGIN;
        String password = MAIL_PASSWORD;
        return new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
    }

    public void stopProcess() {
        stopAutoSending = true;
    }

}
