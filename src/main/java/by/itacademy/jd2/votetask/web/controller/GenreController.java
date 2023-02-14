package by.itacademy.jd2.votetask.web.controller;

import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.dto.GenreDto;
import by.itacademy.jd2.votetask.service.api.IGenreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final IGenreService service;

    public GenreController(IGenreService genreService) {
        this.service = genreService;
    }

    @GetMapping
    public List<Genre> getList() {
        return service.getContent();
    }

    @GetMapping(path = "/{id}")
    public Genre getById(@PathVariable("id") Long genreId){
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody GenreDto genre) {
        service.create(genre);
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable("id") Long genreId,
                       @RequestParam("version") Long version,
                       @RequestBody GenreDto genre) {
        service.update(genre);
//        service.update(genreId,version,genre);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long genreId) {
        service.delete(genreId);
    }
}