package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.dao.factories.GenresDaoSingleton;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.service.GenreService;
import by.itacademy.jd2.votetask.service.MailService;
import by.itacademy.jd2.votetask.service.api.IMailService;

public class MailServiceSingleton {

    private volatile static IMailService instance;

    private MailServiceSingleton() {
    }

    public static IMailService getInstance() {
        if (instance == null) {
            synchronized (MailServiceSingleton.class) {
                if (instance == null) {
                    instance = new MailService();
                }
            }
        }
        return instance;
    }
}
