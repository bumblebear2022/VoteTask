package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.data.GenresList;
import by.itacademy.jd2.votetask.domain.Genre;

import java.util.List;

public class GenresDao implements IGenresDao<Genre> {
    @Override
    public List<Genre> readAll() {
        return GenresList.getGENRES();
    }
}
