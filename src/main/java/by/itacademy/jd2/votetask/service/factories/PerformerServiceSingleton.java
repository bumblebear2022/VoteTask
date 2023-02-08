package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.dao.factories.PerformersDatabaseDaoSingleton;
import by.itacademy.jd2.votetask.service.PerformerService;

public class PerformerServiceSingleton {
    private volatile static PerformerService instance;

    private PerformerServiceSingleton() {
    }

    public static PerformerService getInstance() {
        if(instance == null){
            synchronized (PerformerServiceSingleton.class){
                if (instance == null) {
                    instance = new PerformerService(PerformersDatabaseDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
