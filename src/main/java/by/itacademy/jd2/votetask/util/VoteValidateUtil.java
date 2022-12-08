package by.itacademy.jd2.votetask.util;

import by.itacademy.jd2.votetask.dao.GenresDao;
import by.itacademy.jd2.votetask.dao.PerformersDao;
import by.itacademy.jd2.votetask.dao.factories.GenresDaoSingleton;
import by.itacademy.jd2.votetask.dao.factories.PerformersDaoSingleton;
import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.dto.RequestDto;
import by.itacademy.jd2.votetask.exceptions.InvalidVoteException;

import java.util.ArrayList;
import java.util.List;

public class VoteValidateUtil {
    private final String PERFORMER_DO_NOT_EXIST = "Performer do not exist";
    private final String GENRE_DO_NOT_EXIST = "Genre do not exist";
    private final String PERFORMER_IS_EMPTY = "Performer is empty";
    private final String GENRE_IS_EMPTY = "Genre is empty";
    private final String INFO_IS_EMPTY = "Info is empty";
    private final String WRONG_NUMBER_OF_GENRES = "Wrong number of genres";
    private final int MIN_GENRE_CHOICES = 3;
    private final int MAX_GENRE_CHOICES = 5;
    private final PerformersDao performersDao = PerformersDaoSingleton.getInstance();
    private final GenresDao genresDao = GenresDaoSingleton.getInstance();
    private final List<Performer> performerList = performersDao.readAll();

    private final List<Genre> genresList = genresDao.readAll();
    private final List<String> errors = new ArrayList<>();


    public void validate(RequestDto requestDto) {


        int votesForGenreListSize = requestDto.getVoicesForGenres().size();
        String voiceForPerformer = requestDto.getVoiceForPerformer();
        List<String> voicesForGenres = requestDto.getVoicesForGenres();
        String info = requestDto.getInfo();

        if (voiceForPerformer.isEmpty()) {
            errors.add(PERFORMER_IS_EMPTY);
        }
        if (voicesForGenres.isEmpty()) {
            errors.add(GENRE_IS_EMPTY);
        }
        if (info.isEmpty()) {
            errors.add(INFO_IS_EMPTY);
        }
        if (votesForGenreListSize < MIN_GENRE_CHOICES || votesForGenreListSize > MAX_GENRE_CHOICES) {
            errors.add(WRONG_NUMBER_OF_GENRES);
        }
        boolean validPerformer = performerList.stream()
                .map(Performer::getNickName)
                .anyMatch(nick -> nick.equals(voiceForPerformer));
        if (!validPerformer) {
            errors.add(PERFORMER_DO_NOT_EXIST);
        }
        for (String voiceForGenre : voicesForGenres) {
            boolean validGenre = performerList.stream()
                    .map(Performer::getNickName)
                    .anyMatch(nick -> nick.equals(voiceForGenre));
            if (!validGenre) {
                errors.add(GENRE_DO_NOT_EXIST);
            }
        }
        if (!errors.isEmpty()) {
            throw new InvalidVoteException(errors);
        }
    }
}
