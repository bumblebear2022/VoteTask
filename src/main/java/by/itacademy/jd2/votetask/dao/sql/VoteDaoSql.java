package by.itacademy.jd2.votetask.dao.sql;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.exceptions.DataAccessException;
import by.itacademy.jd2.votetask.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VoteDaoSql implements IVoteDao<SavedVoteDTO> {
    private static final String CREATE_QUERY = "INSERT INTO  data.votes (date_time,about) VALUES (?,?);";
    private static final String CREATE_QUERY_CROSS_PERFORMER = "INSERT INTO  data.vote_genre (id_vote,id_genre) VALUES (?,?);";
    private static final String CREATE_QUERY_CROSS_GENRE = "INSERT INTO  data.vote_performer (id_vote,id_performer) VALUES (?,?);";
    private static final String READ_ALL_QUERY = "SELECT id,name from data.genres";
    private static final String DELETE_QUERY = "DELETE from data.genres where id=?;";



    @Override
    public void create(SavedVoteDTO savedVoteDTO) {
        Long id = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, savedVoteDTO.getCreateDateTime());
            preparedStatement.setString(2, savedVoteDTO.getVote().getAbout());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DataAccessException("SQLException create method :" + e);
        }
        insertIntoVotePerformerByVoteId(savedVoteDTO, id);
        insertIntoVoteGenresByVoteId(savedVoteDTO, id);
    }

    private void insertIntoVotePerformerByVoteId(SavedVoteDTO savedVoteDTO, Long id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY_CROSS_PERFORMER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, savedVoteDTO.getVote().getVoiceForPerformer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQLException create method :" + e);
        }
    }

    private void insertIntoVoteGenresByVoteId(SavedVoteDTO savedVoteDTO, Long id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY_CROSS_GENRE)) {
            List<Long> voicesForGenres = savedVoteDTO.getVote().getVoicesForGenres();
            for (Long genreId : voicesForGenres) {
                preparedStatement.setLong(1, id);
                preparedStatement.setLong(2, genreId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException("SQLException create method :" + e);
        }
    }


    @Override
    public List<SavedVoteDTO> readAll() {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<SavedVoteDTO> entityList = new ArrayList<>();
            while (resultSet.next()) {
                SavedVoteDTO performerDTO = buildEntity(resultSet);
                entityList.add(performerDTO);
            }
            return entityList;
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readAll method :" + e);
        }
    }

    @Override
    public void update() {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<SavedVoteDTO> entityList = new ArrayList<>();
            while (resultSet.next()) {
                SavedVoteDTO performerDTO = buildEntity(resultSet);
                entityList.add(performerDTO);
            }
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readAll method :" + e);
        }
    }

    @Override
    public void delete(SavedVoteDTO savedVoteDTO) {
        Long id = savedVoteDTO.getId();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQLException deleteById method :" + e);
        }
    }

    protected SavedVoteDTO buildEntity(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String about = resultSet.getString("about");
        Long voiceForPerformer = resultSet.getLong("id_performer");
        List<Long> voicesForGenres = null;
        resultSet.getArray("id_genre");
        VoteDto voteDto = new VoteDto(voiceForPerformer, voicesForGenres, about);
        return new SavedVoteDTO(id, voteDto);
    }
}
