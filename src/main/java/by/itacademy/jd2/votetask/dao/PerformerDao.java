package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.domain.Performer;

import java.util.Map;

public class PerformerDao implements Dao <Performer> {
    Performer performer;
    @Override
    public Performer create(String name) {
        performer = new Performer(name);
        return performer;
    }

    @Override
    public void addVoteToEntity() {
    }

    @Override
    public Map<String, Integer> voteResult() {
        return null;
    }
}
