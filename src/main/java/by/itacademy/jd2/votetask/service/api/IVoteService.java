package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.domain.SavedVote;
import by.itacademy.jd2.votetask.dto.VoteDto;

import java.util.List;

public interface IVoteService {
    void addVote(VoteDto voteDto);

    List<SavedVote> getVotes();
}
