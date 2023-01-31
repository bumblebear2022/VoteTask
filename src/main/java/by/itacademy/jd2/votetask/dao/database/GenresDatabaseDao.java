package by.itacademy.jd2.votetask.dao.database;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dto.Genre;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenresDatabaseDao implements IGenresDao {

    private final EntityManager entityManager;

    public GenresDatabaseDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Genre genre) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(genre);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Genre> readAll() {
        List<Genre> resultList = null;
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genre> query = criteriaBuilder.createQuery(Genre.class);
            Root<Genre> root = query.from(Genre.class);
            CriteriaQuery<Genre> select = query.select(root);
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
        boolean isVoted = isVotedForGenre(id);
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
    public void update(Genre genre) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(genre);
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


    private boolean isVotedForGenre(Long id) {
        return false;
    }
}
