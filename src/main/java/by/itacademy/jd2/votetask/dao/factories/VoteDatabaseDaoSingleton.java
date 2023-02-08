package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.VoteDatabaseDao;
import by.itacademy.jd2.votetask.dao.hibernate.EntityManagerFactoryHolder;

public class VoteDatabaseDaoSingleton {
    private volatile static IVoteDao INSTANCE;

    private VoteDatabaseDaoSingleton() {
    }

    public static IVoteDao getInstance() {
        if(INSTANCE == null){
            synchronized (VoteDatabaseDaoSingleton.class){
                if(INSTANCE == null){
                            INSTANCE = new VoteDatabaseDao(EntityManagerFactoryHolder.getInstance());
                }
            }
        }
        return INSTANCE;
    }
}
