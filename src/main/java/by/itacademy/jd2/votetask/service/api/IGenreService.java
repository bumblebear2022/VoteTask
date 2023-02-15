package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.dto.GenreDto;

import java.util.List;

public interface IGenreService {

    List<Genre> getContent();
    boolean exist(Long id);

    List<Genre> getGenres();

    void create(GenreDto genreDto);

    void update(Long id, Integer version, GenreDto genreDto);

    boolean delete(Long id);
}
