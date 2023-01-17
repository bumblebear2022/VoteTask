package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.memory.VoteDao;
import by.itacademy.jd2.votetask.dao.sql.VoteDaoSql;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.storage.StorageOption;
import by.itacademy.jd2.votetask.storage.Switch;

public class VoteDaoSingleton {
    private volatile static IVoteDao<SavedVoteDTO> INSTANCE;
    private static final StorageOption storageOption = Switch.getMode();

    private VoteDaoSingleton() {
    }

    public static IVoteDao<SavedVoteDTO> getInstance() {
        if(INSTANCE == null){
            synchronized (VoteDaoSingleton.class){
                if(INSTANCE == null){
                    switch (storageOption){
                        case  DATABASE:{
                            INSTANCE = new VoteDao();
                            break;
                        }
                        case MEMORY:{
                            INSTANCE = new VoteDaoSql();
                            break;
                        }
                    }
                }
            }
        }
        return INSTANCE;
    }
}
