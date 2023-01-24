package by.itacademy.jd2.votetask.dao.memory.factories;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.memory.GenresMemoryDao;

public class GenresMemoryDaoSingleton {
    private volatile static IGenresDao INSTANCE;

    private GenresMemoryDaoSingleton() {
    }

    public static IGenresDao getInstance() {
        if(INSTANCE == null){
            synchronized (GenresMemoryDaoSingleton.class){
                if(INSTANCE == null){
                            INSTANCE = new GenresMemoryDao();
                }
            }
        }
        return INSTANCE;
    }
}
