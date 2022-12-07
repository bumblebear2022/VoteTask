package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.IGenresDao;
import by.itacademy.jd2.votetask.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class GenreService implements IGenreService {
    private final IGenresDao<Genre> genresDao;

    public GenreService(IGenresDao<Genre> genresDao) {
        this.genresDao = genresDao;
    }

    @Override
    public List<String> getContent() {
        return genresDao.readAll().stream()
                .map(Genre::getTitle)
                .collect(Collectors.toList());
    }
}
