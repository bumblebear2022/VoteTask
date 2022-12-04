package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.domain.Genre;

import java.util.Map;

public class GenreDao implements Dao {

    Genre genre;

    @Override
   public void create(){
        genre = new Genre();
    }

    @Override
    public void addVoteToEntity() {

    }

    @Override
    public Map<String, Integer> voteResult() {
        return null;
    }





}
