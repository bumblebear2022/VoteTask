package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.sql.PerformersDaoSql;
import by.itacademy.jd2.votetask.dto.PerformerDTO;

public class PerformersDaoSingleton {
    private volatile static IPerformersDao<PerformerDTO> INSTANCE;

    private PerformersDaoSingleton() {
    }

    public static IPerformersDao<PerformerDTO> getInstance() {
        if(INSTANCE == null){
            synchronized (PerformersDaoSingleton.class){
                if(INSTANCE == null){
                    INSTANCE = new PerformersDaoSql();
                }
            }
        }
        return INSTANCE;
    }
}
