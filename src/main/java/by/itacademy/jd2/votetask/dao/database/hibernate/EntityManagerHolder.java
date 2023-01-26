package by.itacademy.jd2.votetask.dao.database.hibernate;

import by.itacademy.jd2.votetask.service.factories.PerformerServiceSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHolder {

    private volatile static EntityManager INSTANCE;

    public static EntityManager getInstance() {
        if(INSTANCE == null){
            synchronized (PerformerServiceSingleton.class){
                if (INSTANCE == null) {

                    EntityManagerFactory factory = Persistence.createEntityManagerFactory("votedb");
                    INSTANCE = factory.createEntityManager();
                }
            }
        }
        return INSTANCE;
    }
}
