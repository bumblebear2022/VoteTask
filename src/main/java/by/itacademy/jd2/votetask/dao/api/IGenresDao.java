package by.itacademy.jd2.votetask.dao.api;

import by.itacademy.jd2.votetask.domain.Genre;

public interface IGenresDao extends Dao<Genre> {
    boolean exist(Long id);

    Genre getById(Long id);

    void update(Genre genre);
}
