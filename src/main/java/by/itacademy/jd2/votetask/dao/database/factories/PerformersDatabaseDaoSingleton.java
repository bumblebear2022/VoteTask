package by.itacademy.jd2.votetask.dao.database.factories;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.database.PerformersDatabaseDao;
import by.itacademy.jd2.votetask.dao.database.datasource.DataSourceSingleton;

public class PerformersDatabaseDaoSingleton {
    private volatile static IPerformersDao INSTANCE;

    private PerformersDatabaseDaoSingleton() {
    }

    public static IPerformersDao getInstance() {
        if(INSTANCE == null){
            synchronized (PerformersDatabaseDaoSingleton.class){
                if(INSTANCE == null){
                            INSTANCE = new PerformersDatabaseDao(DataSourceSingleton.getInstance());
                }
            }
        }
        return INSTANCE;
    }
}
