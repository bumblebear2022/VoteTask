package by.itacademy.jd2.votetask.util;

import by.itacademy.jd2.votetask.dto.RequestDto;
import by.itacademy.jd2.votetask.exceptions.InvalidVoteException;

import java.util.ArrayList;
import java.util.List;

public class VoteValidateUtil {
    public static final String PERFORMER_IS_EMPTY = "Performer is empty";
    public static final String GENRE_IS_EMPTY = "Genre is empty";
    public static final String INFO_IS_EMPTY = "Info is empty";
    public static final String WRONG_NUMBER_OF_GENRES = "Wrong number of genres";
    public static final int MIN_GENRE_CHOICES = 3;
    public static final int MAX_GENRE_CHOICES = 5;

    public static void validate(RequestDto requestDto) {
        List<String > errors = new ArrayList<>();
        if (requestDto.getVoiceForPerformer().isEmpty()) {
            errors.add(PERFORMER_IS_EMPTY);
        }
        if (requestDto.getVoicesForGenres().isEmpty()) {
            errors.add(GENRE_IS_EMPTY);
        }
        if (requestDto.getInfo().isEmpty()) {
            errors.add(INFO_IS_EMPTY);
        }
        if (requestDto.getVoicesForGenres().size() < MIN_GENRE_CHOICES
                || requestDto.getVoicesForGenres().size() > MAX_GENRE_CHOICES) {
            errors.add(WRONG_NUMBER_OF_GENRES);
        }
        if(!errors.isEmpty()){
            throw new InvalidVoteException(errors);
        }
    }
}
