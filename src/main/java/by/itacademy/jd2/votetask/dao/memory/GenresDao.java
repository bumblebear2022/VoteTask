package by.itacademy.jd2.votetask.dao.memory;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.factories.VoteDaoSingleton;
import by.itacademy.jd2.votetask.dto.GenreDTO;
import by.itacademy.jd2.votetask.dto.PerformerDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenresDao implements IGenresDao<GenreDTO> {

    private final Map<Long, GenreDTO> genres= new HashMap<>();

    private long idCounter = 10L;

    public GenresDao() {
        genres.put(1L, new GenreDTO(1L,"Genre 1"));
        genres.put(2L, new GenreDTO(2L,"Genre 2"));
        genres.put(3L, new GenreDTO(3L,"Genre 3"));
        genres.put(4L, new GenreDTO(4L,"Genre 4"));
        genres.put(5L, new GenreDTO(5L,"Genre 5"));
        genres.put(6L, new GenreDTO(6L,"Genre 6"));
        genres.put(7L, new GenreDTO(7L,"Genre 7"));
        genres.put(8L, new GenreDTO(8L,"Genre 8"));
        genres.put(9L, new GenreDTO(9L,"Genre 9"));
        genres.put(10L, new GenreDTO(10L,"Genre 10"));
    }

    @Override
    public void create(GenreDTO genreDTO) {
        GenreDTO bufferedDTO = new GenreDTO(createId(), genreDTO.getTitle());
        genres.put(bufferedDTO.getId(), bufferedDTO);
    }

    @Override
    public List<GenreDTO> readAll() {
        return new ArrayList<>(genres.values());
    }

    @Override
    public void update(GenreDTO genreDTO) {
        genres.put(genreDTO.getId(), genreDTO);
    }

    @Override
    public boolean delete(Long id) {
        if (VoteDaoSingleton.getInstance().isVotedGenre(id)) {
            return false;
        } else {
            genres.remove(id);
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
