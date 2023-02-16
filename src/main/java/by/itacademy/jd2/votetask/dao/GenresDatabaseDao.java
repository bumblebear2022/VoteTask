package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenresDatabaseDao implements IGenresDao {
    private static final String CHECK_VOTES_FOR_GENRE = "SELECT EXISTS " +
            "(SELECT * FROM data.vote_genre WHERE id_genre = ?);";
    private final EntityManagerFactory factory;

    public GenresDatabaseDao(EntityManagerFactory factory) {
        this.factory = factory;
    }


    @Override
    public void create(Genre genre) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.persist(genre);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Genre getById(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            return entityManager.find(Genre.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Genre> readAll() {
        EntityManager entityManager = factory.createEntityManager();
        List<Genre> resultList;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genre> query = criteriaBuilder.createQuery(Genre.class);
            Root<Genre> root = query.from(Genre.class);
            CriteriaQuery<Genre> select = query.select(root);
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
        boolean isVoted = isVotedForGenre(id);
        if (isVoted) {
            return false;
        }
        try {
            Genre genreToRemove = entityManager.find(Genre.class, id);
            entityManager.remove(genreToRemove);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Genre genre) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.lock(genre, LockModeType.OPTIMISTIC);
            entityManager.merge(genre);
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
            Genre genre = entityManager.find(Genre.class, id);
            return genre != null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    private boolean isVotedForGenre(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery(CHECK_VOTES_FOR_GENRE);
            query.setParameter(1, id);
            return (boolean) query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }
}
