package by.itacademy.jd2.votetask.dao.database;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.domain.Performer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PerformersDatabaseDao implements IPerformersDao {
    private final EntityManagerFactory factory;

    public PerformersDatabaseDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Performer performer) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(performer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Performer> readAll() {
        EntityManager entityManager = factory.createEntityManager();
        List<Performer> resultList = null;
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Performer> query = criteriaBuilder.createQuery(Performer.class);
            Root<Performer> root = query.from(Performer.class);
            CriteriaQuery<Performer> select = query.select(root);
            resultList = entityManager.createQuery(select).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return resultList;
    }

    @Override
    public boolean delete(Performer performer) {
        EntityManager entityManager = factory.createEntityManager();
        boolean isVoted = isVotedForPerformer(performer.getId());
        if (isVoted) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(performer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public void update(Performer performer) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(performer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean exist(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Performer performer = entityManager.find(Performer.class, id);
            entityManager.getTransaction().commit();
            return performer != null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }


    private boolean isVotedForPerformer(Long id) {
        return false;
    }

}
