package by.itacademy.jd2.votetask.dao.database.factories;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.database.VoteDatabaseDao;
import by.itacademy.jd2.votetask.dao.database.datasource.DataSourceSingleton;

public class VoteDatabaseDaoSingleton {
    private volatile static IVoteDao INSTANCE;

    private VoteDatabaseDaoSingleton() {
    }

    public static IVoteDao getInstance() {
        if(INSTANCE == null){
            synchronized (VoteDatabaseDaoSingleton.class){
                if(INSTANCE == null){
                            INSTANCE = new VoteDatabaseDao(DataSourceSingleton.getInstance());
                }
            }
        }
        return INSTANCE;
    }
}
