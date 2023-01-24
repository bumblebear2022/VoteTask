package by.itacademy.jd2.votetask.dao.api;

import by.itacademy.jd2.votetask.dto.GenreDTO;

public interface IGenresDao extends Dao<GenreDTO> {
    boolean exist(Long id);

    void update(GenreDTO genreDTO);
}
