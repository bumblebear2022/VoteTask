package by.itacademy.jd2.votetask;

import by.itacademy.jd2.votetask.dto.GenreDto;
import by.itacademy.jd2.votetask.dto.PerformerDto;
import by.itacademy.jd2.votetask.service.GenreService;
import by.itacademy.jd2.votetask.service.PerformerService;
import by.itacademy.jd2.votetask.service.api.IGenreService;
import by.itacademy.jd2.votetask.service.api.IPerformerService;
import by.itacademy.jd2.votetask.util.ApplicationContextHolder;

public class Test3 {
    public static void main(String[] args) {
        IGenreService genreService = ApplicationContextHolder.getContext().getBean("GenreServiceBean", GenreService.class);
        IPerformerService performerService = ApplicationContextHolder.getContext().getBean("PerformerServiceBean", PerformerService.class);
        genreService.create(new GenreDto(null, "Genre_1"));
        genreService.create(new GenreDto(null, "Genre_2"));
        genreService.create(new GenreDto(null, "Genre_3"));
        genreService.create(new GenreDto(null, "Genre_4"));
        genreService.create(new GenreDto(null, "Genre_5"));
        performerService.create(new PerformerDto(null, "Performer_1"));
        performerService.create(new PerformerDto(null, "Performer_2"));
        performerService.create(new PerformerDto(null, "Performer_3"));
        performerService.create(new PerformerDto(null, "Performer_4"));
        performerService.create(new PerformerDto(null, "Performer_5"));
    }
}
