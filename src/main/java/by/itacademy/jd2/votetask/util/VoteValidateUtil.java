package by.itacademy.jd2.votetask.util;

import by.itacademy.jd2.votetask.dto.VoteDto;
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

    public static void validate(VoteDto voteDto) {
        List<String > errors = new ArrayList<>();
        if (voteDto.getVoiceForPerformer().isEmpty()) {
            errors.add(PERFORMER_IS_EMPTY);
        }
        if (voteDto.getVoicesForGenres().isEmpty()) {
            errors.add(GENRE_IS_EMPTY);
        }
        if (voteDto.getInfo().isEmpty()) {
            errors.add(INFO_IS_EMPTY);
        }
        if (voteDto.getVoicesForGenres().size() < MIN_GENRE_CHOICES
                || voteDto.getVoicesForGenres().size() > MAX_GENRE_CHOICES) {
            errors.add(WRONG_NUMBER_OF_GENRES);
        }
        if(!errors.isEmpty()){
            throw new InvalidVoteException(errors);
        }
    }
}
