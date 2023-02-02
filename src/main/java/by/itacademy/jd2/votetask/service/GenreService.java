package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.dto.GenreDto;
import by.itacademy.jd2.votetask.service.api.IGenreService;

import java.util.List;

public class GenreService implements IGenreService {
    private final IGenresDao genresDao;


    public GenreService(IGenresDao genresDao) {
        this.genresDao = genresDao;
    }

    @Override
    public List<Genre> getContent() {
        return genresDao.readAll();
    }

    @Override
    public boolean exist(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Genre title can't be empty");
        }
        return genresDao.exist(id);
    }

    @Override
    public void create(GenreDto genreDto) {
        Genre genre = mapDtoToEntity(genreDto);
        genresDao.create(genre);
    }

    private Genre mapDtoToEntity(GenreDto genreDto) {
        Long id = genreDto.getId();
        String title = genreDto.getTitle();
        return new Genre(id, title);
    }

    @Override
    public void update(GenreDto genreDto) {
        Genre genre = mapDtoToEntity(genreDto);
        genresDao.update(genre);
    }

    @Override
    public boolean delete(Long id) {
        return genresDao.delete(id);
    }

    public List<Genre> getGenres() {
        return genresDao.readAll();
    }
}