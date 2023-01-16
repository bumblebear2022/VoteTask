package by.itacademy.jd2.votetask.dao.api;

public interface IVoteDao <T> extends Dao<T> {
    boolean isVotedGenre(Long id);

    boolean isVotedPerformer(Long id);
}
