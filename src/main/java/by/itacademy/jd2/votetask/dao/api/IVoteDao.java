package by.itacademy.jd2.votetask.dao.api;

import by.itacademy.jd2.votetask.domain.SavedVote;

import java.util.List;

public interface IVoteDao extends Dao<SavedVote> {
    boolean isVotedGenre(Long id);

    boolean isVotedPerformer(Long id);

    List<SavedVote> readUnsentVotes();

    void updateSendingInfo(Long id, boolean isOk);
}
