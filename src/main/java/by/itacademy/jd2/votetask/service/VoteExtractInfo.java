package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.domain.About;
import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class VoteExtractInfo {


    private static VoteExtractInfo INSTANCE;




    protected String performer1Name = "Performer1";
    protected String performer2Name = "Performer2";
    protected String performer3Name = "Performer3";
    protected String performer4Name = "Performer4";

    String genre1Name = "Genre1";
    String genre2Name = "Genre2";
    String genre3Name = "Genre3";
    String genre4Name = "Genre4";
    String genre5Name = "Genre5";
    String genre6Name = "Genre6";
    String genre7Name = "Genre7";
    String genre8Name = "Genre8";
    String genre9Name = "Genre9";
    String genre10Name = "Genre10";

    Performer performer1 = new Performer(performer1Name);
    Performer performer2 = new Performer(performer2Name);
    Performer performer3 = new Performer(performer3Name);
    Performer performer4 = new Performer(performer4Name);

    Genre genre1 = new Genre(genre1Name);
    Genre genre2 = new Genre(genre2Name);
    Genre genre3 = new Genre(genre3Name);
    Genre genre4 = new Genre(genre4Name);
    Genre genre5 = new Genre(genre5Name);
    Genre genre6 = new Genre(genre6Name);
    Genre genre7 = new Genre(genre7Name);
    Genre genre8 = new Genre(genre8Name);
    Genre genre9 = new Genre(genre9Name);
    Genre genre10 = new Genre(genre10Name);


    public VoteExtractInfo() {
    }

    public Map<String, Integer> performersToMap() {
        Map<String, Integer> performersMap = new HashMap<>();
        performersMap.put(performer1Name, performer1.getCountVotePerformer());
        performersMap.put(performer2Name, performer2.getCountVotePerformer());
        performersMap.put(performer3Name, performer3.getCountVotePerformer());
        performersMap.put(performer4Name, performer4.getCountVotePerformer());
        return performersMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
    }

    public Map<String, Integer> genresToMap() {
        Map<String, Integer> genresMap = new HashMap<>();
        genresMap.put(genre1Name, genre1.getCountVoteGenre());
        genresMap.put(genre2Name, genre2.getCountVoteGenre());
        genresMap.put(genre3Name, genre3.getCountVoteGenre());
        genresMap.put(genre4Name, genre4.getCountVoteGenre());
        genresMap.put(genre5Name, genre5.getCountVoteGenre());
        genresMap.put(genre6Name, genre6.getCountVoteGenre());
        genresMap.put(genre7Name, genre7.getCountVoteGenre());
        genresMap.put(genre8Name, genre8.getCountVoteGenre());
        genresMap.put(genre9Name, genre9.getCountVoteGenre());
        genresMap.put(genre10Name, genre10.getCountVoteGenre());
        return genresMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
    }

    public boolean infoExtraction(Map<String, String[]> voteMap) {
        int quantityForException = 0;
        for (Map.Entry<String, String[]> parameter : voteMap.entrySet()) {
            boolean isPerformer = isEntity(PERFORMER_LOWER_CASE,parameter);
            boolean isGenre = isEntity(GENRE_LOWER_CASE,parameter);
            boolean isAbout = isEntity(ABOUT_LOWER_CASE,parameter);
            if (isPerformer) {
                for (String performer : parameter.getValue()) {
                        switch (performerParse(performer)) {
                            case 1:
                                performer1.countVotePerformerIncrement();
                                break;
                            case 2:
                                performer2.countVotePerformerIncrement();
                                break;
                            case 3:
                                performer3.countVotePerformerIncrement();
                                break;
                            case 4:
                                performer4.countVotePerformerIncrement();
                                break;
                            default:
                        }
                }
            } else if (isGenre) {
                for (String genre : parameter.getValue()) {
                    quantityForException++;
                    if(parameter.getValue().length >= 3 && parameter.getValue().length<=5) {
                        switch (genreParse(genre)) {
                            case 1:
                                genre1.countVoteGenreIncrement();
                                break;
                            case 2:
                                genre2.countVoteGenreIncrement();
                                break;
                            case 3:
                                genre3.countVoteGenreIncrement();
                                break;
                            case 4:
                                genre4.countVoteGenreIncrement();
                                break;
                            case 5:
                                genre5.countVoteGenreIncrement();
                                break;
                            case 6:
                                genre6.countVoteGenreIncrement();
                                break;
                            case 7:
                                genre7.countVoteGenreIncrement();
                                break;
                            case 8:
                                genre8.countVoteGenreIncrement();
                                break;
                            case 9:
                                genre9.countVoteGenreIncrement();
                                break;
                            case 10:
                                genre10.countVoteGenreIncrement();
                                break;
                        }
                    }
                }
            } else if (isAbout) {
                for (String about : parameter.getValue()) {
                    LocalTime now = LocalTime.now();
                    About.getInstance().addAbout(now, about);
                }
            }
        }
        return quantityForException < 3 || quantityForException > 5;
    }

    private boolean isEntity(String entity,Map.Entry<String, String[]> parameter){
        return parameter.getKey().toLowerCase().contains(entity);
    }


    public Integer performerParse(String performer) {

        Pattern pattern1 = Pattern.compile(performer1Name);
        boolean performer1 = pattern1.matcher(performer).matches();
        if (performer1) {
            return 1;
        }
        Pattern pattern2 = Pattern.compile(performer2Name);
        boolean performer2 = pattern2.matcher(performer).matches();
        if (performer2) {
            return 2;
        }
        Pattern pattern3 = Pattern.compile(performer3Name);
        boolean performer3 = pattern3.matcher(performer).matches();
        if (performer3) {
            return 3;
        }
        Pattern pattern4 = Pattern.compile(performer4Name);
        boolean performer4 = pattern4.matcher(performer).matches();
        if (performer4) {
            return 4;
        }
        return -1;
    }

    public Integer genreParse(String genre) {
        boolean genre1 = genre.toLowerCase().contains(genre1Name.toLowerCase());
        if (genre1) {
            return 1;
        }
        boolean genre2 = genre.toLowerCase().contains(genre2Name.toLowerCase());
        if (genre2) {
            return 2;
        }
        boolean genre3 = genre.toLowerCase().contains(genre3Name.toLowerCase());
        if (genre3) {
            return 3;
        }
        boolean genre4 = genre.toLowerCase().contains(genre4Name.toLowerCase());
        if (genre4) {
            return 4;
        }
        boolean genre5 = genre.toLowerCase().contains(genre5Name.toLowerCase());
        if (genre5) {
            return 5;
        }
        boolean genre6 = genre.toLowerCase().contains(genre6Name.toLowerCase());
        if (genre6) {
            return 6;
        }
        boolean genre7 = genre.toLowerCase().contains(genre7Name.toLowerCase());
        if (genre7) {
            return 7;
        }
        boolean genre8 = genre.toLowerCase().contains(genre8Name.toLowerCase());
        if (genre8) {
            return 8;
        }
        boolean genre9 = genre.toLowerCase().contains(genre9Name.toLowerCase());
        if (genre9) {
            return 9;
        }
        boolean genre10 = genre.toLowerCase().contains(genre10Name.toLowerCase());
        if (genre10) {
            return 10;
        }
        return -1;
    }

    public static VoteExtractInfo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VoteExtractInfo();
        }
        return INSTANCE;
    }
}
