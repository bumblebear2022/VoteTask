package by.itacademy.jd2.votetask.domain;

public class Genre {
    private static final int QUANTITY = 10;

    private String genreName;

    private int countVoteGenre;

    public Genre() {
    }
    public void countVoteGenreIncrement() {
        countVoteGenre += 1;
    }

    public String getGenreName() {
        return genreName;
    }

    public static int getQUANTITY() {
        return QUANTITY;
    }

}
