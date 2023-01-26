package by.itacademy.jd2.votetask.dao.database;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.database.datasource.IDataSourceHolder;
import by.itacademy.jd2.votetask.dao.database.hibernate.EntityManagerHolder;
import by.itacademy.jd2.votetask.dto.GenreDTO;
import by.itacademy.jd2.votetask.exceptions.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        entityManager.getEntityGraphs(Class<GenreDTO>);
        entityManager.getTransaction().commit();
        entityManager.close();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<GenreDTO> entityList = new ArrayList<>();
            while (resultSet.next()) {
                GenreDTO genreDTO = buildGenreDto(resultSet);
                entityList.add(genreDTO);
            }
            return entityList;
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readAll method :" + e);
        }
    }

    @Override
    public boolean delete(Long id) {
        boolean isVoted = isVotedForGenre(id);
        if(isVoted){
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


        entityManager.

        person.setName("Mary");
        session.update(person);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            Long id = genreDTO.getId();
            String title = genreDTO.getTitle();
            preparedStatement.setString(1, title);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQLException update method :" + e);
        }
    }

    @Override
    public boolean exist(Long id) {
        entityManager.contains(new GenreDTO());
    }


    private boolean isVotedForGenre(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_VOTES_FOR_GENRE)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean("exists");
        } catch (SQLException e) {
            throw new DataAccessException("SQLException deleteById method :" + e);
        }
    }
}
