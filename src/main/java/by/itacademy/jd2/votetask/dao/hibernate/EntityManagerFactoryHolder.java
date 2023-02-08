package by.itacademy.jd2.votetask.dao.hibernate;

import by.itacademy.jd2.votetask.service.factories.PerformerServiceSingleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryHolder {

    private volatile static EntityManagerFactory INSTANCE;

    public static EntityManagerFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (PerformerServiceSingleton.class) {
                if (INSTANCE == null) {

                    INSTANCE = Persistence.createEntityManagerFactory("voting");
                }
            }
        }
        return INSTANCE;
    }
}
