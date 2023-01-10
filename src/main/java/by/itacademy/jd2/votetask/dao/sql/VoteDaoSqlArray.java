package by.itacademy.jd2.votetask.dao.sql;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.exceptions.DataAccessException;
import by.itacademy.jd2.votetask.util.DataSource;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VoteDaoSqlArray implements IVoteDao<SavedVoteDTO> {
    private static final String CREATE_QUERY = "INSERT INTO  data.genres (name) VALUES (?,?);";
    private static final String CREATE_QUERY_CROSS_PERFORMER = "INSERT INTO  data.genres (name) VALUES (?,?);";
    private static final String CREATE_QUERY_CROSS_GENRE = "INSERT INTO  data.genres (name) VALUES (?,?);";

    private static final String READ_ALL_QUERY = "SELECT id,name from data.genres";
    private static final String DELETE_QUERY = "DELETE from data.genres where id=?;";
    private static final String EXIST_QUERY = "SELECT EXISTS (SELECT * FROM data.genres WHERE id = ?);";

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

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY_CROSS_PERFORMER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, savedVoteDTO.getVote().getVoiceForPerformer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQLException create method :" + e);
        }

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY_CROSS_GENRE)) {
            preparedStatement.setLong(1, id);
            List<Long> voicesForGenres = savedVoteDTO.getVote().getVoicesForGenres();
            Object[] voicesForGenresArray = voicesForGenres.toArray();
            Array voices = connection.createArrayOf("bigint", voicesForGenresArray);
            preparedStatement.setArray(2, voices);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("SQLException create method :" + e);
        }
    }

//    private static final String READ_BY_IDS_QUERY = """
//            SELECT id,name,price,sale_types
//            from products
//            WHERE id = any (?);""";
//    Array cards = connection.createArrayOf("varchar", cardNumbersArray);
//        preparedStatement.setArray(1, cards);

//    Array keys = connection.createArrayOf("bigint", keyArray);
//        preparedStatement.setArray(1, keys);

    //    Object sale_types = resultSet.getArray("sale_types").getArray();
//    String[] saleTypesArray = (String[]) sale_types;
//    Set<SaleType> saleTypes = Arrays.stream(saleTypesArray)
//            .map(SaleType::valueOf)
//            .collect(Collectors.toSet());
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
