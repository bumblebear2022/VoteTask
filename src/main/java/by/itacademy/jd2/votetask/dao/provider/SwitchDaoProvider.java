package by.itacademy.jd2.votetask.dao.provider;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.provider.api.IDaoProvider;

public class SwitchDaoProvider implements IDaoProvider {

    private boolean useDB = true;

    private volatile static SwitchDaoProvider INSTANCE;

    private  IDaoProvider daoProvider;

    private SwitchDaoProvider() {
        if(useDB){
            daoProvider = new DatabaseDaoProvider();
        } else {
            daoProvider = new MemoryDaoProvider();
        }
    }

    @Override
    public IGenresDao genreDao() {
        return daoProvider.genreDao();
    }

    @Override
    public IPerformersDao performerDao() {
        return daoProvider.performerDao();
    }

    @Override
    public IVoteDao voteDao() {
        return daoProvider.voteDao();
    }

    public static IDaoProvider getInstance() {
        if(INSTANCE == null){
            synchronized (SwitchDaoProvider.class){
                if(INSTANCE == null){
                    INSTANCE = new SwitchDaoProvider();
                }
            }
        }
        return INSTANCE;
    }
}
