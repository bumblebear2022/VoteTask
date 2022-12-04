package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.domain.Performer;

import java.util.Map;

public class PerformerDao implements Dao {
    Performer performer;
    @Override
    public void create() {
        performer = new Performer();
    }

    @Override
    public void addVoteToEntity() {
    }

    @Override
    public Map<String, Integer> voteResult() {
        return null;
    }
}
