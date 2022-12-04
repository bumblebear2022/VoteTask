package by.itacademy.jd2.votetask.domain;

public class Performer {
    private static final int QUANTITY = 4;
    private String performerName;
    private int countVotePerformer;

    public Performer(String performerName) {
        this.performerName = performerName;
    }

    public void countVotePerformerIncrement() {
        countVotePerformer += 1;
    }

    public static int getQUANTITY() {
        return QUANTITY;
    }

}
