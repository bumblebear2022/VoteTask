package by.itacademy.jd2.votetask.dao;

import java.util.Map;

public interface Dao <T> {
    T create(String name);
    void addVoteToEntity();
    Map<String,Integer> voteResult();
}
