package by.itacademy.jd2.votetask.dao.database;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dto.Performer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PerformersDatabaseDao implements IPerformersDao {
    private final EntityManager entityManager;

    public PerformersDatabaseDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Performer performer) {
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
    public boolean delete(Long id) {
        boolean isVoted = isVotedForPerformer(id);
        if (isVoted) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(id);
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
        boolean contains = false;
        try {
            entityManager.getTransaction().begin();
            contains = entityManager.contains(id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return contains;
    }


    private boolean isVotedForPerformer(Long id) {
        return false;
    }
}
