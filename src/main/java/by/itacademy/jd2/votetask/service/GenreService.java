package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.content.GenresContentHolder;
import by.itacademy.jd2.votetask.content.IGenresDao;

import java.util.List;

public class GenreService implements IGenreService{
    public static final String HEADER = "<p><b>Choose 3-5 genres:</b></p>";

    private final IGenresDao iGenresDao = new GenresContentHolder();

    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public List<String> getContent() {
        return iGenresDao.readAll();
    }
}
