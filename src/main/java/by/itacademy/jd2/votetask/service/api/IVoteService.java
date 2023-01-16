package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.dto.VoteDto;

import java.util.List;

public interface IVoteService {
    void addVote(VoteDto voteDto);

    List<SavedVoteDTO> getVotes();
}
