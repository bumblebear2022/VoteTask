package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.dao.factories.PerformersDaoSingleton;
import by.itacademy.jd2.votetask.dao.factories.PerformersDaoSqlSingleton;
import by.itacademy.jd2.votetask.provider.StorageOption;
import by.itacademy.jd2.votetask.provider.Switch;
import by.itacademy.jd2.votetask.service.PerformerService;

public class PerformerServiceSingleton {
    private volatile static PerformerService instance;
    private static final StorageOption storageOption = Switch.getMode();

    private PerformerServiceSingleton() {
    }

    public static PerformerService getInstance() {
        if(instance == null){
            synchronized (PerformerServiceSingleton.class){
                switch (storageOption){
                    case  DATABASE:{
                        instance = new PerformerService(PerformersDaoSqlSingleton.getInstance());
                        break;
                    }
                    case MEMORY:{
                        instance = new PerformerService(PerformersDaoSingleton.getInstance());
                        break;
                    }
                }
            }
        }
        return instance;
    }
}
