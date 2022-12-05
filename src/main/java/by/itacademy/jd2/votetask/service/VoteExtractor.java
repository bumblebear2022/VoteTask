package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.domain.Vote;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class VoteExtractor {

    private static final String PERFORMER_LOWER_CASE = "performer";
    private static final String GENRE_LOWER_CASE = "genre";
    private static final String ABOUT_LOWER_CASE = "about";

    public Vote extract(Map<String, String[]> voteMap){
        String[] performers = voteMap.get(PERFORMER_LOWER_CASE);
        String performer = performers[0];
        String[] genres = voteMap.get(GENRE_LOWER_CASE);
        List<String> genresList = Arrays.asList(genres);
        String[] abouts = voteMap.get(ABOUT_LOWER_CASE);
        String about = abouts[0];
        return new Vote(performer,genresList,about);

    }
}
