package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.sql.GenresDaoSql;
import by.itacademy.jd2.votetask.dto.GenreDTO;

public class GenresDaoSqlSingleton {
    private volatile static IGenresDao<GenreDTO> instance;

    private GenresDaoSqlSingleton() {
    }

    public static IGenresDao<GenreDTO> getInstance() {
        if(instance == null){
            synchronized (GenresDaoSqlSingleton.class){
                if(instance == null){
                    instance = new GenresDaoSql();
                }
            }
        }
        return instance;
    }
}
