package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.sql.PerformersDaoSql;
import by.itacademy.jd2.votetask.dto.PerformerDTO;

public class PerformersDaoSqlSingleton {
    private volatile static IPerformersDao<PerformerDTO> INSTANCE;

    private PerformersDaoSqlSingleton() {
    }

    public static IPerformersDao<PerformerDTO> getInstance() {
        if(INSTANCE == null){
            synchronized (PerformersDaoSqlSingleton.class){
                if(INSTANCE == null){
                    INSTANCE = new PerformersDaoSql();
                }
            }
        }
        return INSTANCE;
    }
}
