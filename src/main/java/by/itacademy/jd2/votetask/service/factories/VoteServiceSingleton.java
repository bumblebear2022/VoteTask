package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.dao.provider.SwitchDaoProvider;
import by.itacademy.jd2.votetask.service.VoteService;

public class VoteServiceSingleton {
    private volatile static VoteService instance;

    private VoteServiceSingleton() {
    }

    public static VoteService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceSingleton.class) {
                if (instance == null) {
                    instance = new VoteService(
                            SwitchDaoProvider.getInstance().voteDao(),
                            PerformerServiceSingleton.getInstance(),
                            GenreServiceSingleton.getInstance(),
                            MailServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}

