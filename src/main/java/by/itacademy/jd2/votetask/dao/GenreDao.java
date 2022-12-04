package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.domain.Genre;

import java.util.Map;

public class GenreDao implements Dao<Genre> {

    Genre genre;

    @Override
    public Genre create(String name) {
        genre = new Genre(name);
        return genre;
    }

    @Override
    public void addVoteToEntity() {

    }

    @Override
    public Map<String, Integer> voteResult() {
        return null;
    }

}
