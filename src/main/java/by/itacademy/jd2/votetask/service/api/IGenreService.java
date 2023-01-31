package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.dto.Genre;

import java.util.List;

public interface IGenreService {

    List<Genre> getContent();
    boolean exist(Long id);

    List<Genre> getGenres();

    void create(Genre genre);

    void update(Genre genre);

    boolean delete(Long id);
}
