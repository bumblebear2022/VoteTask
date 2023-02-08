package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.GenresDatabaseDao;
import by.itacademy.jd2.votetask.dao.hibernate.EntityManagerFactoryHolder;

public class GenresDatabaseDaoSingleton {
    private volatile static IGenresDao INSTANCE;

    private GenresDatabaseDaoSingleton() {
    }

    public static IGenresDao getInstance() {
        if(INSTANCE == null){
            synchronized (GenresDatabaseDaoSingleton.class){
                if(INSTANCE == null){
                            INSTANCE = new GenresDatabaseDao(EntityManagerFactoryHolder.getInstance());
                    }
                }
            }
        return INSTANCE;
    }
}
