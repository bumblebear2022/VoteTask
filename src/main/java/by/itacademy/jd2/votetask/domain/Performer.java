package by.itacademy.jd2.votetask.domain;

import java.util.HashMap;
import java.util.Map;

public class Performer {
    private static Performer INSTANCE;

    private String namePerformer1;
    private String namePerformer2;
    private String namePerformer3;
    private String namePerformer4;

    private Integer countVotePerformer1 = 0;
    private Integer countVotePerformer2 = 0;
    private Integer countVotePerformer3 = 0;
    private Integer countVotePerformer4 = 0;

    private Performer() {
        namePerformer1 = "performer1";
        namePerformer2 = "performer2";
        namePerformer3 = "performer3";
        namePerformer4 = "performer4";
    }

    public static Performer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Performer();
        }
        return INSTANCE;
    }

    public Map <String,Integer> performersToMap() {
        Map<String, Integer> performersMap = new HashMap<>();
        performersMap.put(namePerformer1, countVotePerformer1);
        performersMap.put(namePerformer2, countVotePerformer2);
        performersMap.put(namePerformer3, countVotePerformer3);
        performersMap.put(namePerformer4, countVotePerformer4);
        return performersMap;
    }

    public void countVotePerformer1Increment() {
        countVotePerformer1 += 1;
    }

    public void countVotePerformer2Increment() {
        countVotePerformer2 += 1;
    }

    public void countVotePerformer3Increment() {
        countVotePerformer3 += 1;
    }

    public void countVotePerformer4Increment() {
        countVotePerformer4 += 1;
    }

    public String getNamePerformer1() {
        return namePerformer1;
    }

    public String getNamePerformer2() {
        return namePerformer2;
    }

    public String getNamePerformer3() {
        return namePerformer3;
    }

    public String getNamePerformer4() {
        return namePerformer4;
    }
}
