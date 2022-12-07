package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.domain.Vote;

import java.util.ArrayList;
import java.util.List;

public class VoteDao implements IVoteDao<Vote> {

    List<Vote> voteList = new ArrayList<>();

    @Override
    public void create(Vote vote) {
        voteList.add(vote);
    }
    @Override
    public List<Vote> readAll() {
        return voteList;
    }

    @Override
    public void delete(Vote vote) {
        voteList.remove(vote);
    }


}
