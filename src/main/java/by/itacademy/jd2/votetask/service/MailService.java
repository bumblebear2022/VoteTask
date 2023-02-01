package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.domain.SavedVote;
import by.itacademy.jd2.votetask.service.api.IGenreService;
import by.itacademy.jd2.votetask.service.api.IMailService;
import by.itacademy.jd2.votetask.service.api.IPerformerService;
import by.itacademy.jd2.votetask.service.factories.GenreServiceSingleton;
import by.itacademy.jd2.votetask.service.factories.PerformerServiceSingleton;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class MailService implements IMailService {

    public static final String SMTP_SERVER = "smtp.mail.ru";
    public static final String SMTP_PORT = "465";
    public static final String SUBJECT = "Group 2 voting app";
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final IPerformerService performerService = PerformerServiceSingleton.getInstance();

    private final IGenreService genreService = GenreServiceSingleton.getInstance();

    @Override
    public void sendMail(SavedVote savedVote) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_SERVER);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", SMTP_PORT);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, getAuthenticator());

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("votingmailbot@mail.ru"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("statkevichne@gmail.com"));

            message.setSubject(SUBJECT);

            String text = parseTextFromVote(savedVote);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String parseTextFromVote(SavedVote savedVote) {
        LocalDateTime createDateTime = savedVote.getCreateDateTime();
        String voteTime = createDateTime.format(format);


        Long voiceForPerformer = savedVote.getVoiceForPerformer().getId();
        List<Performer> performerList = performerService.getContent();
        String performer = performerList.stream()
                .filter(performerDTO -> performerDTO.getId().equals(voiceForPerformer))
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

        List<Genre> voicesForGenres = vote.getVoicesForGenres();
        List<String> votedGenresList = voicesForGenres.stream()
                .map(x-> genresMap.get(x.getId()))
                .map(Genre::getTitle)
                .collect(Collectors.toList());

        return String.join(", ", votedGenresList);
    }

    private Authenticator getAuthenticator() {
        String username = "votingmailbot@mail.ru";
        String password = "ANPqu0CPaCmPbs1raFgx";
        return new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
    }

}
