package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.dao.factories.VoteDaoSqlSingleton;
import by.itacademy.jd2.votetask.service.VoteService;

public class VoteServiceSingleton {
    private volatile static VoteService instance;

    private VoteServiceSingleton() {
    }

    public static VoteService getInstance() {
        if(instance == null){
            synchronized (VoteServiceSingleton.class){
                if(instance == null){
                    instance = new VoteService(
                            VoteDaoSqlSingleton.getInstance(),
                            PerformerServiceSingleton.getInstance(),
                            GenreServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}

