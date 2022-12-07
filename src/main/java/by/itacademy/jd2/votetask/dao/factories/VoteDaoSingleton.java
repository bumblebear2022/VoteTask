package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.VoteDao;

public class VoteDaoSingleton {
    private volatile static VoteDao instance;

    public static VoteDao getInstance() {
        if(instance == null){
            synchronized (VoteDaoSingleton.class){
                if(instance == null){
                    instance = new VoteDao();
                }
            }
        }
        return instance;
    }
}
