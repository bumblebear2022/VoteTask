package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.dao.provider.SwitchDaoProvider;
import by.itacademy.jd2.votetask.service.PerformerService;

public class PerformerServiceSingleton {
    private volatile static PerformerService instance;

    private PerformerServiceSingleton() {
    }

    public static PerformerService getInstance() {
        if(instance == null){
            synchronized (PerformerServiceSingleton.class){
                if (instance == null) {
                    instance = new PerformerService(SwitchDaoProvider.getInstance().performerDao());
                }
            }
        }
        return instance;
    }
}
