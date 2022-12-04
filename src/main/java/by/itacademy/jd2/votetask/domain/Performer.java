package by.itacademy.jd2.votetask.domain;

public class Performer {
    private static final int QUANTITY = 4;
    private String performerName;
    private int countVotePerformer;

    public Performer() {
    }

    public void countVotePerformerIncrement() {
        countVotePerformer += 1;
    }


    public String getNamePerformer() {
        return performerName;
    }

    public static int getQUANTITY() {
        return QUANTITY;
    }

}
