package by.itacademy.jd2.votetask.dao.sql;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dto.PerformerDTO;
import by.itacademy.jd2.votetask.exceptions.DataAccessException;
import by.itacademy.jd2.votetask.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerformersDaoSql implements IPerformersDao<PerformerDTO> {
    private static final String CREATE_QUERY = "INSERT INTO  data.performers (name) VALUES (?);";
    private static final String READ_ALL_QUERY = "SELECT id,name from data.performers";
    private static final String DELETE_QUERY = "DELETE from data.performers where id=?;";
    private static final String EXIST_QUERY = "SELECT EXISTS (SELECT * FROM data.performers WHERE id = ?);";
    private static final String UPDATE_QUERY = "UPDATE data.performers SET name = ? WHERE id=?;";

    @Override
    public void create(PerformerDTO performerDTO) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, performerDTO.getNickName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQLException create method :" + e);
        }
    }

    @Override
    public List<PerformerDTO> readAll() {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<PerformerDTO> entityList = new ArrayList<>();
            while (resultSet.next()) {
                PerformerDTO performerDTO = buildPerformerDto(resultSet);
                entityList.add(performerDTO);
            }
            return entityList;
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readAll method :" + e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new DataAccessException("SQLException deleteById method :" + e);
        }
    }

    @Override
    public void update(PerformerDTO performerDTO) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            Long id = performerDTO.getId();
            String name = performerDTO.getNickName();
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQLException update method :" + e);
        }
    }

    @Override
    public boolean exist(Long id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXIST_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean("exists");
        } catch (SQLException e) {
            throw new DataAccessException("SQLException deleteById method :" + e);
        }
    }


    protected PerformerDTO buildPerformerDto(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new PerformerDTO(id, name);
    }
}
