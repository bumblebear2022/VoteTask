package by.itacademy.jd2.votetask.domain;

import java.util.HashMap;
import java.util.Map;

public class Genre {
    private static Genre INSTANCE;

    private String genre1;
    private String genre2;
    private String genre3;
    private String genre4;
    private String genre5;
    private String genre6;
    private String genre7;
    private String genre8;
    private String genre9;
    private String genre10;

    private int countVoteGenre1 ;
    private int countVoteGenre2;
    private int countVoteGenre3;
    private int countVoteGenre4;
    private int countVoteGenre5;
    private int countVoteGenre6;
    private int countVoteGenre7;
    private int countVoteGenre8;
    private int countVoteGenre9;
    private int countVoteGenre10;


    private Genre() {
        genre1 ="genre1";
        genre2 ="genre2";
        genre3 ="genre3";
        genre4 ="genre4";
        genre5 ="genre5";
        genre6 ="genre6";
        genre7 ="genre7";
        genre8 ="genre8";
        genre9 ="genre9";
        genre10 ="genre10";
    }

    public static Genre getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Genre();
        }
        return INSTANCE;
    }

    public Map <String,Integer> genresToMap() {
        Map<String, Integer> genresMap = new HashMap<>();
        genresMap.put(genre1, countVoteGenre1);
        genresMap.put(genre2, countVoteGenre2);
        genresMap.put(genre3, countVoteGenre3);
        genresMap.put(genre4, countVoteGenre4);
        genresMap.put(genre5, countVoteGenre5);
        genresMap.put(genre6, countVoteGenre6);
        genresMap.put(genre7, countVoteGenre7);
        genresMap.put(genre8, countVoteGenre8);
        genresMap.put(genre9, countVoteGenre9);
        genresMap.put(genre10, countVoteGenre10);
        return genresMap;
    }
    public void countVoteGenre1Increment() {
        countVoteGenre1 += 1;
    }

    public void countVoteGenre2Increment() {
        countVoteGenre2 += 1;
    }

    public void countVoteGenre3Increment() {
        countVoteGenre3 += 1;
    }

    public void countVoteGenre4Increment() {
        countVoteGenre4 += 1;
    }
    public void countVoteGenre5Increment() {
        countVoteGenre5 += 1;
    }

    public void countVoteGenre6Increment() {
        countVoteGenre6 += 1;
    }
    public void countVoteGenre7Increment() {
        countVoteGenre7 += 1;
    }
    public void countVoteGenre8Increment() {
        countVoteGenre8 += 1;
    }
    public void countVoteGenre9Increment() {
        countVoteGenre9 += 1;
    }
    public void countVoteGenre10Increment() {
        countVoteGenre10 += 1;
    }


    public String getGenre1() {
        return genre1;
    }

    public String getGenre2() {
        return genre2;
    }

    public String getGenre3() {
        return genre3;
    }

    public String getGenre4() {
        return genre4;
    }

    public String getGenre5() {
        return genre5;
    }

    public String getGenre6() {
        return genre6;
    }

    public String getGenre7() {
        return genre7;
    }

    public String getGenre8() {
        return genre8;
    }

    public String getGenre9() {
        return genre9;
    }

    public String getGenre10() {
        return genre10;
    }
}
