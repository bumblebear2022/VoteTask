package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.dao.factories.GenresDaoSingleton;
import by.itacademy.jd2.votetask.dao.factories.GenresDaoSqlSingleton;
import by.itacademy.jd2.votetask.provider.StorageOption;
import by.itacademy.jd2.votetask.provider.Switch;
import by.itacademy.jd2.votetask.service.GenreService;

public class GenreServiceSingleton {
    private volatile static GenreService instance;

    private static final StorageOption storageOption = Switch.getMode();

    private GenreServiceSingleton() {
    }

    public static GenreService getInstance() {
        if(instance == null){
            synchronized (GenreServiceSingleton.class){
                if(instance == null){
                    switch (storageOption){
                        case  DATABASE:{
                            instance = new GenreService(GenresDaoSqlSingleton.getInstance());
                            break;
                        }
                        case MEMORY:{
                            instance = new GenreService(GenresDaoSingleton.getInstance());
                            break;
                        }
                    }
                }
            }
        }
        return instance;
    }
}
