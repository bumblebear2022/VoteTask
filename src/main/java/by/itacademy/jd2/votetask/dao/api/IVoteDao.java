package by.itacademy.jd2.votetask.dao.api;

import by.itacademy.jd2.votetask.dto.SavedVoteDTO;

public interface IVoteDao extends Dao<SavedVoteDTO> {
    boolean isVotedGenre(Long id);

    boolean isVotedPerformer(Long id);
}
