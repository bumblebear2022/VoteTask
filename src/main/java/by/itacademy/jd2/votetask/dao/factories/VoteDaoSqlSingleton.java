package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.memory.VoteDao;
import by.itacademy.jd2.votetask.dao.sql.VoteDaoSql;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;

public class VoteDaoSqlSingleton {
    private volatile static IVoteDao<SavedVoteDTO> instance;

    private VoteDaoSqlSingleton() {
    }

    public static IVoteDao<SavedVoteDTO> getInstance() {
        if(instance == null){
            synchronized (VoteDaoSqlSingleton.class){
                if(instance == null){
                    instance = new VoteDaoSql();
                }
            }
        }
        return instance;
    }
}
