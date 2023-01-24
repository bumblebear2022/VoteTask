package by.itacademy.jd2.votetask.dao.provider;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.database.factories.GenresDatabaseDaoSingleton;
import by.itacademy.jd2.votetask.dao.database.factories.PerformersDatabaseDaoSingleton;
import by.itacademy.jd2.votetask.dao.database.factories.VoteDatabaseDaoSingleton;
import by.itacademy.jd2.votetask.dao.provider.api.IDaoProvider;

public class DatabaseDaoProvider implements IDaoProvider {
    @Override
    public IGenresDao genreDao() {
        return GenresDatabaseDaoSingleton.getInstance();
    }

    @Override
    public IPerformersDao performerDao() {
        return PerformersDatabaseDaoSingleton.getInstance();
    }

    @Override
    public IVoteDao voteDao() {
        return VoteDatabaseDaoSingleton.getInstance();
    }
}
