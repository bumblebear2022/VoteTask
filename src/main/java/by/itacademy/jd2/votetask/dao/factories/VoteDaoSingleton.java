package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.memory.VoteDao;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;

public class VoteDaoSingleton {
    private volatile static IVoteDao<SavedVoteDTO> instance;

    private VoteDaoSingleton() {
    }

    public static IVoteDao<SavedVoteDTO> getInstance() {
        if(instance == null){
            synchronized (by.itacademy.jd2.votetask.dao.factories.VoteDaoSqlSingleton.class){
                if(instance == null){
                    instance = new VoteDao();
                }
            }
        }
        return instance;
    }
}
