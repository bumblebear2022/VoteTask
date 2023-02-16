package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.domain.Performer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PerformersDatabaseDao implements IPerformersDao {
    private final EntityManagerFactory factory;

    public PerformersDatabaseDao(EntityManagerFactory factory) {
        this.factory = factory;
    }
    private static final String CHECK_VOTES_FOR_PERFORMER = "SELECT EXISTS (SELECT * FROM data.vote_performer WHERE id_performer = ?);";

    @Override
    public void create(Performer performer) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.persist(performer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Performer getById(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            return entityManager.find(Performer.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Performer> readAll() {
        EntityManager entityManager = factory.createEntityManager();
        List<Performer> resultList;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Performer> query = criteriaBuilder.createQuery(Performer.class);
            Root<Performer> root = query.from(Performer.class);
            CriteriaQuery<Performer> select = query.select(root);
            resultList = entityManager.createQuery(select).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return resultList;
    }

    @Override
    public boolean delete(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        boolean isVoted = isVotedForPerformer(id);
        if (isVoted) {
            return false;
        }
        try {
            Performer performerToRemove = entityManager.find(Performer.class, id);
            entityManager.remove(performerToRemove);
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
            entityManager.lock(performer, LockModeType.OPTIMISTIC);
            entityManager.merge(performer);
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
            Performer performer = entityManager.find(Performer.class, id);
            return performer != null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    private boolean isVotedForPerformer(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery(CHECK_VOTES_FOR_PERFORMER);
            query.setParameter(1, id);
            return (boolean) query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }
}
