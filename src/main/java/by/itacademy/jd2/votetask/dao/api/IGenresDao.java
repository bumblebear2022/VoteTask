package by.itacademy.jd2.votetask.dao.api;

import by.itacademy.jd2.votetask.dto.Genre;

public interface IGenresDao extends Dao<Genre> {
    boolean exist(Long id);

    void update(Genre genre);
}
