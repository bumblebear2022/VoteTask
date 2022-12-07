package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.GenresDao;

public class GenresDaoSingleton {
    private volatile static GenresDao instance;

    public static GenresDao getInstance() {
        if(instance == null){
            synchronized (GenresDaoSingleton.class){
                if(instance == null){
                    instance = new GenresDao();
                }
            }
        }
        return instance;
    }
}
