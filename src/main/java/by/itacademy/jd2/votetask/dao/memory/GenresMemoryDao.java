package by.itacademy.jd2.votetask.dao.memory;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.database.factories.VoteDatabaseDaoSingleton;
import by.itacademy.jd2.votetask.domain.Genre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenresMemoryDao implements IGenresDao {

    private final Map<Long, Genre> genres= new HashMap<>();

    private long idCounter = 10L;

    public GenresMemoryDao() {
        genres.put(1L, new Genre(1L,"Genre 1"));
        genres.put(2L, new Genre(2L,"Genre 2"));
        genres.put(3L, new Genre(3L,"Genre 3"));
        genres.put(4L, new Genre(4L,"Genre 4"));
        genres.put(5L, new Genre(5L,"Genre 5"));
        genres.put(6L, new Genre(6L,"Genre 6"));
        genres.put(7L, new Genre(7L,"Genre 7"));
        genres.put(8L, new Genre(8L,"Genre 8"));
        genres.put(9L, new Genre(9L,"Genre 9"));
        genres.put(10L, new Genre(10L,"Genre 10"));
    }

    @Override
    public void create(Genre genre) {
        Genre bufferedDTO = new Genre(createId(), genre.getTitle());
        genres.put(bufferedDTO.getId(), bufferedDTO);
    }

    @Override
    public List<Genre> readAll() {
        return new ArrayList<>(genres.values());
    }

    @Override
    public void update(Genre genre) {
        genres.put(genre.getId(), genre);
    }

    @Override
    public boolean delete(Genre genre) {
        if (VoteDatabaseDaoSingleton.getInstance().isVotedGenre(genre.getId())) {
            return false;
        } else {
            genres.remove(genre.getId());
            return true;
        }
    }

    @Override
    public boolean exist(Long id) {
        return genres.get(id) != null;
    }

    private long createId() {
        idCounter++;
        return idCounter;
    }
}
