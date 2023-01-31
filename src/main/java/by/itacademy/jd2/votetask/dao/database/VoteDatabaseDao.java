package by.itacademy.jd2.votetask.dao.database;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.domain.SavedVote;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class VoteDatabaseDao implements IVoteDao {
    private final EntityManager entityManager;

    public VoteDatabaseDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(SavedVote savedVote) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(savedVote);
            entityManager.flush();
            Long id = savedVote.getId();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public List<SavedVote> readAll() {
        List<SavedVote> resultList = null;
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<SavedVote> query = criteriaBuilder.createQuery(SavedVote.class);
            Root<SavedVote> root = query.from(SavedVote.class);
            CriteriaQuery<SavedVote> select = query.select(root);
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
    public boolean delete(SavedVote savedVote) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(savedVote);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public boolean isVotedGenre(Long id) {
        return false;
    }

    @Override
    public boolean isVotedPerformer(Long id) {
        return false;
    }
}
