package by.itacademy.jd2.votetask.dao.database;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.database.hibernate.EntityManagerHolder;
import by.itacademy.jd2.votetask.dto.GenreDTO;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenresDatabaseDao implements IGenresDao {

    private final EntityManager entityManager = EntityManagerHolder.getInstance();

    @Override
    public void create(GenreDTO genreDTO) {
        String title = genreDTO.getTitle();
        entityManager.getTransaction().begin();
        entityManager.persist(new GenreDTO(title));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<GenreDTO> readAll() {

        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GenreDTO> query = criteriaBuilder.createQuery(GenreDTO.class);
        Root<GenreDTO> root = query.from(GenreDTO.class);
        CriteriaQuery<GenreDTO> select = query.select(root);
        List<GenreDTO> resultList = entityManager.createQuery(select).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return resultList;
    }

    @Override
    public boolean delete(Long id) {
        boolean isVoted = isVotedForGenre(id);
        if (isVoted) {
            return false;
        }
        GenreDTO genreDTO = entityManager.find(GenreDTO.class, id);
        entityManager.remove(genreDTO);
        entityManager.flush();
        entityManager.clear();
        return true;
    }

    @Override
    public void update(GenreDTO genreDTO) {
        entityManager.getTransaction().begin();
        entityManager.merge(genreDTO);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public boolean exist(Long id) {
        return entityManager.contains(id);
    }


    private boolean isVotedForGenre(Long id) {
        return true;
    }
}
