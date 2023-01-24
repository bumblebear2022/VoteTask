package by.itacademy.jd2.votetask.dao.provider.api;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.api.IVoteDao;

public interface IDaoProvider{
    IGenresDao genreDao();
    IPerformersDao performerDao();
    IVoteDao voteDao();
}
