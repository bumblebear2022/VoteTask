package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.PerformersDatabaseDao;
import by.itacademy.jd2.votetask.dao.hibernate.EntityManagerFactoryHolder;

public class PerformersDatabaseDaoSingleton {
    private volatile static IPerformersDao INSTANCE;

    private PerformersDatabaseDaoSingleton() {
    }

    public static IPerformersDao getInstance() {
        if(INSTANCE == null){
            synchronized (PerformersDatabaseDaoSingleton.class){
                if(INSTANCE == null){
                            INSTANCE = new PerformersDatabaseDao(EntityManagerFactoryHolder.getInstance());
                }
            }
        }
        return INSTANCE;
    }
}
