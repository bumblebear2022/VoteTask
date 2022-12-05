package by.itacademy.jd2.votetask.service.genre;

import by.itacademy.jd2.votetask.content.GenresContentHolder;
import by.itacademy.jd2.votetask.content.IGenresDao;
import by.itacademy.jd2.votetask.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class GenreService implements IGenreService {
    private static final String HEADER = "<p><b>Choose 3-5 genres:</b></p>";
    private static final String FOOTER = "<p><b>Also write few words in about section...</b></p>";

    private final IGenresDao<Genre> genresDao = new GenresContentHolder();

    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public String getFooter() {
        return FOOTER;
    }

    @Override
    public List<String> getContent() {
        return genresDao.readAll().stream()
                .map(Genre::getTitle)
                .collect(Collectors.toList());
    }
}
