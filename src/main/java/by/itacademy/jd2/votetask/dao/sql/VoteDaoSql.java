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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteDaoSql implements IVoteDao<SavedVoteDTO> {
    private static final String CREATE_QUERY = "INSERT INTO  data.votes (date_time,about) VALUES (?,?);";
    private static final String CREATE_QUERY_CROSS_PERFORMER = "INSERT INTO  data.vote_performer (id_vote,id_performer) VALUES (?,?);";
    private static final String CREATE_QUERY_CROSS_GENRE = "INSERT INTO  data.vote_genre (id_vote,id_genre) VALUES (?,?);";
    private static final String READ_ALL_QUERY = "SELECT id,date_time,about from data.votes";
    private static final String READ_ALL_CROSS_PERFORMER = "SELECT id_vote, id_performer from data.vote_performer";
    private static final String READ_ALL_CROSS_GENRE = "SELECT id_vote, id_genre from data.vote_genre";
    private static final String DELETE_QUERY = "DELETE from data.genres where id=?;";
    private static final String CHECK_VOTES_FOR_GENRE = "SELECT EXISTS (SELECT * FROM data.vote_genre WHERE id_genre = ?);";
    private static final String CHECK_VOTES_FOR_PERFORMER = "SELECT EXISTS (SELECT * FROM data.vote_performer WHERE id_performer = ?);";



    @Override
    public void create(SavedVoteDTO savedVoteDTO) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            Timestamp timestamp = Timestamp.valueOf(savedVoteDTO.getCreateDateTime());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setString(2, savedVoteDTO.getVote().getAbout());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                Long id = resultSet.getLong(1);
                insertIntoVotePerformerByVoteId(savedVoteDTO, id);
                insertIntoVoteGenresByVoteId(savedVoteDTO, id);
            }
        } catch (SQLException e) {
            throw new DataAccessException("SQLException create method :" + e);
        }
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
        Map<Long, SavedVoteDTO> baseVotes = readVotes();
        Map<Long, SavedVoteDTO> votesWithPerformers = addVoteCrossPerformer(baseVotes);
        Map<Long, SavedVoteDTO> votesWithGenres = addVoteCrossGenre(votesWithPerformers);
        return new ArrayList<>(votesWithGenres.values());
    }

    private Map<Long, SavedVoteDTO> readVotes() {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Map<Long, SavedVoteDTO> savedVoteDTOMap = new HashMap<>();
            while (resultSet.next()) {
                Long voteId = resultSet.getLong("id");
                LocalDateTime dt = resultSet.getTimestamp("date_time").toLocalDateTime();
                String about = resultSet.getString("about");
                VoteDto voteDto = new VoteDto(null, new ArrayList<>(), about);
                SavedVoteDTO savedVoteDTO = new SavedVoteDTO(voteId, dt, voteDto);
                savedVoteDTOMap.put(voteId, savedVoteDTO);
            }
            return savedVoteDTOMap;
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readAll method :" + e);
        }
    }

    private Map<Long, SavedVoteDTO>  addVoteCrossPerformer(Map<Long, SavedVoteDTO>  savedVoteDTOS) {
        try (Connection connection = DataSource.getConnection();
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
        try (Connection connection = DataSource.getConnection();
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
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
           return preparedStatement.execute();
        } catch (SQLException e) {
            throw new DataAccessException("SQLException deleteById method :" + e);
        }
    }

    @Override
    public boolean checkVotesForGenre(Long id) {
        try (Connection connection = DataSource.getConnection();
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
    public boolean checkVotesForPerformer(Long id) {
        try (Connection connection = DataSource.getConnection();
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
