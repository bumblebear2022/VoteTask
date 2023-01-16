package by.itacademy.jd2.votetask.dao.sql;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dto.GenreDTO;
import by.itacademy.jd2.votetask.exceptions.DataAccessException;
import by.itacademy.jd2.votetask.util.DataSourceHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenresDaoSql implements IGenresDao<GenreDTO> {

    private static final String CREATE_QUERY = "INSERT INTO  data.genres (name) VALUES (?);";
    private static final String READ_ALL_QUERY = "SELECT id,name from data.genres";
    private static final String DELETE_QUERY = "DELETE from data.genres where id=?;";
    private static final String EXIST_QUERY = "SELECT EXISTS (SELECT * FROM data.genres WHERE id = ?);";
    private static final String UPDATE_QUERY = "UPDATE data.genres SET name = ? WHERE id=?;";
    private static final String CHECK_VOTES_FOR_GENRE = "SELECT EXISTS (SELECT * FROM data.vote_genre WHERE id_genre = ?);";
    private final DataSource dataSource = DataSourceHolder.getDataSource();

    @Override
    public void create(GenreDTO genreDTO) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, genreDTO.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQLException create method :" + e);
        }
    }

    @Override
    public List<GenreDTO> readAll() {
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
        boolean isVoted = checkVotesForGenre(id);
        if(isVoted){
            return false;
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows != 0;
        } catch (SQLException e) {
            throw new DataAccessException("SQLException deleteById method :" + e);
        }
    }

    @Override
    public void update(GenreDTO genreDTO) {
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXIST_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean("exists");
        } catch (SQLException e) {
            throw new DataAccessException("SQLException deleteById method :" + e);
        }
    }


    private boolean checkVotesForGenre(Long id) {
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


    protected GenreDTO buildGenreDto(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new GenreDTO(id, name);
    }
}
