package by.itacademy.jd2.votetask.dao;

import java.util.Map;

public interface Dao {
    void create();
    void addVoteToEntity();
    Map<String,Integer> voteResult();
}
