package by.itacademy.jd2.votetask.domain;

public class Genre {
    private static final int QUANTITY = 10;

    private String genreName;

    private int countVoteGenre;

    public Genre(String genreName) {
        this.genreName = genreName;
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
