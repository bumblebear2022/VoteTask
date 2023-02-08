package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.domain.SavedVote;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.exceptions.DataAccessException;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteDatabaseDao implements IVoteDao {
    private final EntityManagerFactory factory;

    public VoteDatabaseDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(SavedVote savedVote) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(savedVote);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<SavedVote> readAll() {
        EntityManager entityManager = factory.createEntityManager();
        List<SavedVote> resultList = null;
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<SavedVote> query = criteriaBuilder.createQuery(SavedVote.class);
            Root<SavedVote> root = query.from(SavedVote.class);
            CriteriaQuery<SavedVote> select = query.select(root);
            EntityGraph<SavedVote> entityGraph = entityManager.createEntityGraph(SavedVote.class);
            entityGraph.addAttributeNodes("voiceForPerformer","voicesForGenres");
            resultList = entityManager
                    .createQuery(select)
                    .setHint("javax.persistence.fetchgraph",entityGraph)
                    .getResultList();
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
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            SavedVote voteToRemove = entityManager.find(SavedVote.class, id);
            entityManager.remove(voteToRemove);
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

    public List<SavedVote> readUnsentVotes() {
        EntityManager entityManager = factory.createEntityManager();
        List<SavedVote> unsentVotesList = null;
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<SavedVote> query = criteriaBuilder.createQuery(SavedVote.class);
            Root<SavedVote> root = query.from(SavedVote.class);
            CriteriaQuery<SavedVote> select = query.where(
                    criteriaBuilder.lt(root.get("sendingAttempts"), 3),
                    criteriaBuilder.equal(root.get("isSent"), false)
            );
            EntityGraph<SavedVote> entityGraph = entityManager.createEntityGraph(SavedVote.class);
            entityGraph.addAttributeNodes("voiceForPerformer","voicesForGenres");
            TypedQuery<SavedVote> typedQuery = entityManager.createQuery(select).setHint("javax.persistence.fetchgraph",entityGraph);
            unsentVotesList = typedQuery.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return unsentVotesList;
    }

    public void updateSendingInfo(Long id, boolean isOk) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            SavedVote bufferedVote = entityManager.find(SavedVote.class, id);
            if (isOk) {
                bufferedVote.setIsSent(true);
            } else {
                bufferedVote.setSendingAttempts(bufferedVote.getSendingAttempts() + 1);
            }
            entityManager.merge(bufferedVote);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }
}
