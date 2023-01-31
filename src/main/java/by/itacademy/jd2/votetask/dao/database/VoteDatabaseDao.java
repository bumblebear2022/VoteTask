package by.itacademy.jd2.votetask.dao.database;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.exceptions.DataAccessException;
import by.itacademy.jd2.votetask.dao.database.datasource.IDataSourceHolder;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteDatabaseDao implements IVoteDao{
    private final EntityManager entityManager;

    public VoteDatabaseDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(SavedVoteDTO savedVoteDTO) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(savedVoteDTO);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    private void insertIntoVotePerformerByVoteId(SavedVoteDTO savedVoteDTO, Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY_CROSS_PERFORMER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, savedVoteDTO.getVote().getVoiceForPerformer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQLException create method :" + e);
        }
    }

    private void insertIntoVoteGenresByVoteId(SavedVoteDTO savedVoteDTO, Long id) {
        try (Connection connection = dataSource.getConnection();
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
        Map<Long, SavedVoteDTO> baseVotes = readVotes();
        Map<Long, SavedVoteDTO> votesWithPerformers = addVoteCrossPerformer(baseVotes);
        Map<Long, SavedVoteDTO> votesWithGenres = addVoteCrossGenre(votesWithPerformers);
        return new ArrayList<>(votesWithGenres.values());
    }

    private Map<Long, SavedVoteDTO> readVotes() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Map<Long, SavedVoteDTO> savedVoteDTOMap = new HashMap<>();
            while (resultSet.next()) {
                Long voteId = resultSet.getLong("id");
                LocalDateTime dt = resultSet.getTimestamp("date_time").toLocalDateTime();
                String about = resultSet.getString("about");
                String email = resultSet.getString("email");
                VoteDto voteDto = new VoteDto(null, new ArrayList<>(), about, email);
                SavedVoteDTO savedVoteDTO = new SavedVoteDTO(voteId, dt, voteDto);
                savedVoteDTOMap.put(voteId, savedVoteDTO);
            }
            return savedVoteDTOMap;
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readAll method :" + e);
        }
    }

    private Map<Long, SavedVoteDTO>  addVoteCrossPerformer(Map<Long, SavedVoteDTO>  savedVoteDTOS) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_CROSS_PERFORMER);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Long voteId = resultSet.getLong("id_vote");
                Long performerId = resultSet.getLong("id_performer");
                SavedVoteDTO savedVoteDTO = savedVoteDTOS.get(voteId);
                VoteDto vote = savedVoteDTO.getVote();
                vote.setVoiceForPerformer(performerId);
            }
            return savedVoteDTOS;
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readAll method :" + e);
        }
    }

    private Map<Long, SavedVoteDTO>  addVoteCrossGenre(Map<Long, SavedVoteDTO>  savedVoteDTOS) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_CROSS_GENRE);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Long voteId = resultSet.getLong("id_vote");
                Long genreId = resultSet.getLong("id_genre");
                SavedVoteDTO savedVoteDTO = savedVoteDTOS.get(voteId);
                VoteDto vote = savedVoteDTO.getVote();
                List<Long> voicesForGenres = vote.getVoicesForGenres();
                voicesForGenres.add(genreId);
            }
            return savedVoteDTOS;
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readAll method :" + e);
        }
    }


    @Override
    public boolean delete(Long id) {
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
    public boolean isVotedGenre(Long id) {
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
    @Override
    public boolean isVotedPerformer(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_VOTES_FOR_PERFORMER)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean("exists");
        } catch (SQLException e) {
            throw new DataAccessException("SQLException deleteById method :" + e);
        }
    }
}
