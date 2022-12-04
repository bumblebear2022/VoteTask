package by.itacademy.jd2.votetask.utils;

import by.itacademy.jd2.votetask.domain.About;
import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;

import java.util.Map;

public final class VoteInfoExtractUtil {

    private static final String PERFORMER_LOWER_CASE = "performer";
    private static final String GENRE_LOWER_CASE = "genre";
    private static final String ABOUT_LOWER_CASE = "about";

    private VoteInfoExtractUtil() {
    }


    public static void infoExtraction(Map<String, String[]> voteMap) {

        for (Map.Entry<String, String[]> parameter : voteMap.entrySet()) {
            String key = parameter.getKey().toLowerCase();

            if (key.contains(PERFORMER_LOWER_CASE)) {
                int result;
                for (String performer : parameter.getValue()) {
                    result = PerformerParseUtil.performerParse(performer);
                    switch (result) {
                        case 1:
                            Performer.getInstance().countVotePerformer1Increment();
                            break;
                        case 2:
                            Performer.getInstance().countVotePerformer2Increment();
                            break;
                        case 3:
                            Performer.getInstance().countVotePerformer3Increment();
                            break;
                        case 4:
                            Performer.getInstance().countVotePerformer4Increment();
                            break;
                        default:
                    }
                }
            } else if (key.contains(GENRE_LOWER_CASE)) {
                int result;
                for (String genre : parameter.getValue()) {
                    result = GenreParseUtil.genreParse(genre);
                    switch (result) {
                        case 1:
                            Genre.getInstance().countVoteGenre1Increment();
                            break;
                        case 2:
                            Genre.getInstance().countVoteGenre2Increment();
                            break;
                        case 3:
                            Genre.getInstance().countVoteGenre3Increment();
                            break;
                        case 4:
                            Genre.getInstance().countVoteGenre4Increment();
                            break;
                        case 5:
                            Genre.getInstance().countVoteGenre5Increment();
                            break;
                        case 6:
                            Genre.getInstance().countVoteGenre6Increment();
                            break;
                        case 7:
                            Genre.getInstance().countVoteGenre7Increment();
                            break;
                        case 8:
                            Genre.getInstance().countVoteGenre8Increment();
                            break;
                        case 9:
                            Genre.getInstance().countVoteGenre9Increment();
                            break;
                        case 10:
                            Genre.getInstance().countVoteGenre10Increment();
                            break;
                    }
                }
            } else if (key.contains(ABOUT_LOWER_CASE)) {
                for (String about : parameter.getValue()) {
                    About.getInstance().addAbout(about);
                }
            }
        }
    }
}
