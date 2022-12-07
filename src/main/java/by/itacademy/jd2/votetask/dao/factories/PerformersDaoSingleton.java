package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.PerformersDao;

public class PerformersDaoSingleton {
    private volatile static PerformersDao instance;

    public static PerformersDao getInstance() {
        if(instance == null){
            synchronized (PerformersDaoSingleton.class){
                if(instance == null){
                    instance = new PerformersDao();
                }
            }
        }
        return instance;
    }
}
