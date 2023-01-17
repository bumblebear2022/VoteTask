package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.memory.PerformersDao;
import by.itacademy.jd2.votetask.dao.sql.PerformersDaoSql;
import by.itacademy.jd2.votetask.dto.PerformerDTO;
import by.itacademy.jd2.votetask.storage.StorageOption;
import by.itacademy.jd2.votetask.storage.Switch;

public class PerformersDaoSingleton {
    private volatile static IPerformersDao<PerformerDTO> INSTANCE;
    private static final StorageOption storageOption = Switch.getMode();

    private PerformersDaoSingleton() {
    }

    public static IPerformersDao<PerformerDTO> getInstance() {
        if(INSTANCE == null){
            synchronized (PerformersDaoSingleton.class){
                if(INSTANCE == null){
               switch (storageOption){
                        case  DATABASE:{
                            INSTANCE = new PerformersDao();
                            break;
                        }
                        case MEMORY:{
                            INSTANCE = new PerformersDaoSql();
                            break;
                        }
                    }
                }
            }
        }
        return INSTANCE;
    }
}
