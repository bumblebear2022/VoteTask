package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.sql.GenresDaoSql;
import by.itacademy.jd2.votetask.dto.GenreDTO;

public class GenresDaoSingleton {
    private volatile static IGenresDao<GenreDTO> instance;

    private GenresDaoSingleton() {
    }

    public static IGenresDao<GenreDTO> getInstance() {
        if(instance == null){
            synchronized (GenresDaoSingleton.class){
                if(instance == null){
                    instance = new GenresDaoSql();
                }
            }
        }
        return instance;
    }
}
