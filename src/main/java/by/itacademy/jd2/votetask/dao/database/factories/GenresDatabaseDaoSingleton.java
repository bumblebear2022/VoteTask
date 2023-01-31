package by.itacademy.jd2.votetask.dao.database.factories;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.database.GenresDatabaseDao;
import by.itacademy.jd2.votetask.dao.database.hibernate.EntityManagerHolder;

public class GenresDatabaseDaoSingleton {
    private volatile static IGenresDao INSTANCE;

    private GenresDatabaseDaoSingleton() {
    }

    public static IGenresDao getInstance() {
        if(INSTANCE == null){
            synchronized (GenresDatabaseDaoSingleton.class){
                if(INSTANCE == null){
                            INSTANCE = new GenresDatabaseDao(EntityManagerHolder.getInstance());
                    }
                }
            }
        return INSTANCE;
    }
}
