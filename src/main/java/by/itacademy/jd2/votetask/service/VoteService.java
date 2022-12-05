package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.content.VotesContentHolder;
import by.itacademy.jd2.votetask.domain.Vote;

public class VoteService {

    VotesContentHolder votesContentHolder = new VotesContentHolder();

    public void addVote(Vote vote){
        votesContentHolder.performerVoteIncrement(vote);
        votesContentHolder.genreVoteIncrement(vote);
        votesContentHolder.addVoteInfo(vote);
    }

}
