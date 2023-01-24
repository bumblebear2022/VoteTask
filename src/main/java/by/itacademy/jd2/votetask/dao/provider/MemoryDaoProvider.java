package by.itacademy.jd2.votetask.dao.provider;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.memory.factories.GenresMemoryDaoSingleton;
import by.itacademy.jd2.votetask.dao.memory.factories.PerformersMemoryDaoSingleton;
import by.itacademy.jd2.votetask.dao.memory.factories.VoteMemoryDaoSingleton;
import by.itacademy.jd2.votetask.dao.provider.api.IDaoProvider;

public class MemoryDaoProvider implements IDaoProvider{
    @Override
    public IGenresDao genreDao() {
        return GenresMemoryDaoSingleton.getInstance();
    }

    @Override
    public IPerformersDao performerDao() {
        return PerformersMemoryDaoSingleton.getInstance();
    }

    @Override
    public IVoteDao voteDao() {
        return VoteMemoryDaoSingleton.getInstance();
    }
}
