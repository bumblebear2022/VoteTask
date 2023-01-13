package by.itacademy.jd2.votetask.util;

import by.itacademy.jd2.votetask.dao.memory.GenresDao;
import by.itacademy.jd2.votetask.dao.memory.PerformersDao;
import by.itacademy.jd2.votetask.dao.memory.VoteDao;
import by.itacademy.jd2.votetask.dao.sql.GenresDaoSql;
import by.itacademy.jd2.votetask.dao.sql.PerformersDaoSql;
import by.itacademy.jd2.votetask.dao.sql.VoteDaoSql;
import by.itacademy.jd2.votetask.service.GenreService;
import by.itacademy.jd2.votetask.service.PerformerService;
import by.itacademy.jd2.votetask.service.StatisticsService;
import by.itacademy.jd2.votetask.service.VoteService;
import by.itacademy.jd2.votetask.service.api.IGenreService;
import by.itacademy.jd2.votetask.service.api.IPerformerService;
import by.itacademy.jd2.votetask.service.api.IStatisticsService;
import by.itacademy.jd2.votetask.service.api.IVoteService;

public class ServiceProvider {

    private final IVoteService voteService;
    private final IPerformerService performerService;
    private final IStatisticsService statisticsService;
    private final IGenreService genreService;
    private static volatile ServiceProvider instance;

    private ServiceProvider() {
        if ("DB".equals(Switch.getMode())) {
            performerService = new PerformerService(new PerformersDaoSql());
            genreService = new GenreService(new GenresDaoSql());
            voteService = new VoteService(new VoteDaoSql(), performerService, genreService);
        } else {
            performerService = new PerformerService(new PerformersDao());
            genreService = new GenreService(new GenresDao());
            voteService = new VoteService(new VoteDao(), performerService, genreService);
        }
        statisticsService = new StatisticsService();
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            synchronized (ServiceProvider.class) {
                if (instance == null) {
                    instance = new ServiceProvider();
                }
            }
        }
        return instance;
    }

    public IVoteService getVoteService() {
        return voteService;
    }

    public IPerformerService getPerformerService() {
        return performerService;
    }

    public IStatisticsService getStatisticsService() {
        return statisticsService;
    }

    public IGenreService getGenreService() {
        return genreService;
    }
}
