package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    List<GenreDTO> getContent();
    boolean exist(Long id);

    List<GenreDTO> getGenres();

    void create(GenreDTO genreDTO);

    void update(GenreDTO genreDTO);

    boolean delete(GenreDTO genreDTO);
}
