package by.itacademy.jd2.votetask.dao.memory.factories;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.memory.VoteMemoryDao;

public class VoteMemoryDaoSingleton {
    private volatile static IVoteDao INSTANCE;

    private VoteMemoryDaoSingleton() {
    }

    public static IVoteDao getInstance() {
        if(INSTANCE == null){
            synchronized (VoteMemoryDaoSingleton.class){
                if(INSTANCE == null){
                            INSTANCE = new VoteMemoryDao();
                }
            }
        }
        return INSTANCE;
    }
}
