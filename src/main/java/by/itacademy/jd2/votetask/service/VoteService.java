package by.itacademy.jd2.votetask.service;


import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;

import java.util.ArrayList;
import java.util.List;

public class VoteService {

    private final int PERFORMERS_QUANTITY = Performer.getQUANTITY();

    private final int GENRES_QUANTITY = Genre.getQUANTITY();

    private List<Performer> performersProduction(){
        List<Performer> performers = new ArrayList<>();
        for (int i =0; i<PERFORMERS_QUANTITY;i++){
            Performer performer = new Performer();
            performers.add(performer);
        }
        return performers;
    }

    private List<Genre> genresProduction(){
        List<Genre> genres = new ArrayList<>();
        for (int i =0; i<GENRES_QUANTITY;i++){
            Genre genre = new Genre();
            genres.add(genre);
        }
        return genres;
    }


}

