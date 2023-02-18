package by.itacademy.jd2.votetask.dao.api;

import by.itacademy.jd2.votetask.domain.SavedVote;

import java.util.List;

public interface IVoteDao extends Dao<SavedVote> {
    List<SavedVote> readUnsentVotes();

    void updateSendingInfo(Long id, boolean isOk);
}
