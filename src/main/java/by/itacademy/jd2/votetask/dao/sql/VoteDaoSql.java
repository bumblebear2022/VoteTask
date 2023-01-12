package by.itacademy.jd2.votetask.dao.sql;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dto.CrossGenreDto;
import by.itacademy.jd2.votetask.dto.CrossPerformerDto;
import by.itacademy.jd2.votetask.dto.MainVoteDto;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.exceptions.DataAccessException;
import by.itacademy.jd2.votetask.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VoteDaoSql implements IVoteDao<SavedVoteDTO> {
    private static final String CREATE_QUERY = "INSERT INTO  data.votes (date_time,about) VALUES (?,?);";
    private static final String CREATE_QUERY_CROSS_PERFORMER = "INSERT INTO  data.vote_genre (id_vote,id_genre) VALUES (?,?);";
    private static final String CREATE_QUERY_CROSS_GENRE = "INSERT INTO  data.vote_performer (id_vote,id_performer) VALUES (?,?);";
    private static final String READ_ALL_QUERY = "SELECT id,date_time,about from data.votes";
    private static final String READ_ALL_CROSS_PERFORMER = "SELECT id_vote, id_performer from data.vote_performer";
    private static final String READ_ALL_CROSS_GENRE = "SELECT id_vote, id_genre from data.vote_genre";
    private static final String DELETE_QUERY = "DELETE from data.genres where id=?;";

    private static final String EXIST_QUERY = "SELECT EXISTS (SELECT * FROM data.genres WHERE id = ?);";


    @Override
    public void create(SavedVoteDTO savedVoteDTO) {

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, savedVoteDTO.getCreateDateTime());
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
        List<MainVoteDto> baseVotes = readVotes();
        List<CrossPerformerDto> votesForPerformers = readVoteCrossPerformer();
        List<CrossGenreDto> votesForGenres = readVoteCrossGenre();

        Map<Long, List<Long>> collectPerformers = votesForPerformers.stream()
                .collect(Collectors.groupingBy(CrossPerformerDto::getVoteId,
                        Collectors.mapping(CrossPerformerDto::getPerformerId, Collectors.toList())));

        Map<Long, List<Long>> collectGenres = votesForGenres.stream()
                .collect(Collectors.groupingBy(CrossGenreDto::getVoteId,
                        Collectors.mapping(CrossGenreDto::getGenreId, Collectors.toList())));

        List<SavedVoteDTO> savedVoteDTOS = new ArrayList<>();

        for(MainVoteDto vote : baseVotes){
            Long id = vote.getId();
            LocalDateTime createDateTime = vote.getCreateDateTime();
            Long voteForPerformer = collectPerformers.get(id).get(0);
            List<Long> votesForGenresList = collectGenres.get(id);

            VoteDto voteDto = new VoteDto(voteForPerformer, votesForGenresList, vote.getAbout());
            new SavedVoteDTO(id,createDateTime,voteDto);
        }

        return savedVoteDTOS;
    }

    private List<MainVoteDto> readVotes() {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<MainVoteDto> entityMap = new ArrayList<>();
            while (resultSet.next()) {
                Long voteId = resultSet.getLong("id");
                LocalDateTime dt = (LocalDateTime) resultSet.getObject("date_time");
                String about = resultSet.getString("about");

                MainVoteDto mainVoteDto = new MainVoteDto(voteId,dt,about);
                entityMap.add(mainVoteDto);
            }
            return entityMap;
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readAll method :" + e);
        }
    }



    private List<CrossPerformerDto> readVoteCrossPerformer() {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_CROSS_PERFORMER);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<CrossPerformerDto> entityList = new ArrayList<>();
            while (resultSet.next()) {
                Long voteId = resultSet.getLong("id_vote");
                Long performerId = resultSet.getLong("id_performer");
                entityList.add(new CrossPerformerDto(voteId, performerId));
            }
            return entityList;
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readAll method :" + e);
        }
    }

    private List<CrossGenreDto> readVoteCrossGenre() {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_CROSS_GENRE);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<CrossGenreDto> entityList = new ArrayList<>();
            while (resultSet.next()) {
                Long voteId = resultSet.getLong("id_vote");
                Long genreId = resultSet.getLong("id_genre");
                entityList.add(new CrossGenreDto(voteId, genreId));
            }
            return entityList;
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
}
