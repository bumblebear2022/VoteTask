package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.Dao;
import by.itacademy.jd2.votetask.dao.GenreDao;
import by.itacademy.jd2.votetask.dao.PerformerDao;
import by.itacademy.jd2.votetask.domain.About;
import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;

import java.util.Map;

public final class VoteExtractInfo {

    private static final String PERFORMER_LOWER_CASE = "performer";
    private static final String GENRE_LOWER_CASE = "genre";
    private static final String ABOUT_LOWER_CASE = "about";

    String performer1Name = "Performer 1";
    String performer2Name = "Performer 2";
    String performer3Name = "Performer 3";
    String performer4Name = "Performer 4";

    String genre1Name = "Genre 1";
    String genre2Name = "Genre 2";
    String genre3Name = "Genre 3";
    String genre4Name = "Genre 4";
    String genre5Name = "Genre 5";
    String genre6Name = "Genre 6";
    String genre7Name = "Genre 7";
    String genre8Name = "Genre 8";
    String genre9Name = "Genre 9";
    String genre10Name = "Genre 10";



    Performer performer1;
    Performer performer2;
    Performer performer3;
    Performer performer4;
    Genre genre1;
    Genre genre2;
    Genre genre3;
    Genre genre4;
    Genre genre5;
    Genre genre6;
    Genre genre7;
    Genre genre8;
    Genre genre9;
    Genre genre10;


    public VoteExtractInfo() {
        performersInit();
        genresInit();
    }

    public void infoExtraction(Map<String, String[]> voteMap) {

        for (Map.Entry<String, String[]> parameter : voteMap.entrySet()) {
            String key = parameter.getKey().toLowerCase();

            if (key.contains(PERFORMER_LOWER_CASE)) {
                int result;
                for (String performer : parameter.getValue()) {

                    result = performerParse(performer);
                    switch (result) {
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
            } else if (key.contains(GENRE_LOWER_CASE)) {
                int result;
                for (String genre : parameter.getValue()) {
                    result = genreParse(genre);
                    switch (result) {
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
            } else if (key.contains(ABOUT_LOWER_CASE)) {
                for (String about : parameter.getValue()) {
                    About.getInstance().addAbout(about);
                }
            }
        }
    }
    public Integer performerParse(String performer) {
        boolean performer1 = performer.toLowerCase().contains(performer1Name);
        if (performer1) {
            return 1;
        }
        boolean performer2 = performer.toLowerCase().contains(performer2Name);
        if (performer2) {
            return 2;
        }
        boolean performer3 = performer.toLowerCase().contains(performer3Name);
        if (performer3) {
            return 3;
        }
        boolean performer4 = performer.toLowerCase().contains(performer4Name);
        if (performer4) {
            return 4;
        }
        return -1;
    }

    public  Integer genreParse(String genre) {
        boolean genre1 = genre.toLowerCase().contains(genre1Name);
        if (genre1) {
            return 1;
        }
        boolean genre2 = genre.toLowerCase().contains(genre2Name);
        if (genre2) {
            return 2;
        }
        boolean genre3 = genre.toLowerCase().contains(genre3Name);
        if (genre3) {
            return 3;
        }
        boolean genre4 = genre.toLowerCase().contains(genre4Name);
        if (genre4) {
            return 4;
        }
        boolean genre5 = genre.toLowerCase().contains(genre5Name);
        if (genre5) {
            return 5;
        }
        boolean genre6 = genre.toLowerCase().contains(genre6Name);
        if (genre6) {
            return 6;
        }
        boolean genre7 = genre.toLowerCase().contains(genre7Name);
        if (genre7) {
            return 7;
        }
        boolean genre8 = genre.toLowerCase().contains(genre8Name);
        if (genre8) {
            return 8;
        }
        boolean genre9 = genre.toLowerCase().contains(genre9Name);
        if (genre9) {
            return 9;
        }
        boolean genre10 = genre.toLowerCase().contains(genre10Name);
        if (genre10) {
            return 10;
        }
        return -1;
    }

    private void performersInit() {
        Dao<Performer> performerDao = new PerformerDao();
        Performer performer1 = performerDao.create(performer1Name);
        Performer performer2 = performerDao.create(performer2Name);
        Performer performer3 = performerDao.create(performer3Name);
        Performer performer4 = performerDao.create(performer4Name);
    }

    private void genresInit() {
        Dao<Genre> genreDao = new GenreDao();
        Genre genre1 = genreDao.create(genre1Name);
        Genre genre2 = genreDao.create(genre2Name);
        Genre genre3 = genreDao.create(genre3Name);
        Genre genre4 = genreDao.create(genre4Name);
        Genre genre5 = genreDao.create(genre5Name);
        Genre genre6 = genreDao.create(genre6Name);
        Genre genre7 = genreDao.create(genre7Name);
        Genre genre8 = genreDao.create(genre8Name);
        Genre genre9 = genreDao.create(genre9Name);
        Genre genre10 = genreDao.create(genre10Name);
    }
}
