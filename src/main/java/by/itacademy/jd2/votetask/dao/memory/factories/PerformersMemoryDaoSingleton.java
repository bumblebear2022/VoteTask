package by.itacademy.jd2.votetask.dao.memory.factories;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.memory.PerformersMemoryDao;

public class PerformersMemoryDaoSingleton {
    private volatile static IPerformersDao INSTANCE;

    private PerformersMemoryDaoSingleton() {
    }

    public static IPerformersDao getInstance() {
        if(INSTANCE == null){
            synchronized (PerformersMemoryDaoSingleton.class){
                if(INSTANCE == null){
                            INSTANCE = new PerformersMemoryDao();
                }
            }
        }
        return INSTANCE;
    }
}
