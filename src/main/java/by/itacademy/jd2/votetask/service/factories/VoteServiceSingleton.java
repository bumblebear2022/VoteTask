package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.dao.factories.VoteDaoSingleton;
import by.itacademy.jd2.votetask.dao.factories.VoteDaoSqlSingleton;
import by.itacademy.jd2.votetask.provider.StorageOption;
import by.itacademy.jd2.votetask.provider.Switch;
import by.itacademy.jd2.votetask.service.VoteService;

public class VoteServiceSingleton {
    private volatile static VoteService instance;
    private static final StorageOption storageOption = Switch.getMode();

    private VoteServiceSingleton() {
    }

    public static VoteService getInstance() {
        if(instance == null){
            synchronized (VoteServiceSingleton.class){
                if(instance == null){
                    switch (storageOption){
                        case  DATABASE:{
                            instance = new VoteService(
                                    VoteDaoSqlSingleton.getInstance(),
                                    PerformerServiceSingleton.getInstance(),
                                    GenreServiceSingleton.getInstance());
                            break;
                        }
                        case MEMORY:{
                            instance = new VoteService(
                                    VoteDaoSingleton.getInstance(),
                                    PerformerServiceSingleton.getInstance(),
                                    GenreServiceSingleton.getInstance());
                            break;
                        }
                    }
                }
            }
        }
        return instance;
    }
}

