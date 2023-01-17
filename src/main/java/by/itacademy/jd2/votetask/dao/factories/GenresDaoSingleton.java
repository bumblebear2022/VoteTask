package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.memory.GenresDao;
import by.itacademy.jd2.votetask.dao.sql.GenresDaoSql;
import by.itacademy.jd2.votetask.dto.GenreDTO;
import by.itacademy.jd2.votetask.storage.StorageOption;
import by.itacademy.jd2.votetask.storage.Switch;

public class GenresDaoSingleton {
    private volatile static IGenresDao<GenreDTO> INSTANCE;
    private static final StorageOption storageOption = Switch.getMode();

    private GenresDaoSingleton() {
    }

    public static IGenresDao<GenreDTO> getInstance() {
        if(INSTANCE == null){
            synchronized (GenresDaoSingleton.class){
                if(INSTANCE == null){
                    switch (storageOption){
                        case  DATABASE:{
                            INSTANCE = new GenresDao();
                            break;
                        }
                        case MEMORY:{
                            INSTANCE = new GenresDaoSql();
                            break;
                        }
                    }
                }
            }
        }
        return INSTANCE;
    }
}
