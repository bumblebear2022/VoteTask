package by.itacademy.jd2.votetask.content;

import java.util.List;

public class GenresContentHolder implements IGenresDao {

    private static final List<String> GENRES = List.of("Genre 1", "Genre 2", "Genre 3", "Genre 4", "Genre 5",
            "Genre 6", "Genre 7", "Genre 8", "Genre 9", "Genre 10");

    @Override
    public List<String> readAll() {
        return GENRES;
    }
}
