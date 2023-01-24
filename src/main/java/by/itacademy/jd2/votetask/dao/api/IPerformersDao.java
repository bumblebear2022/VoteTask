package by.itacademy.jd2.votetask.dao.api;

import by.itacademy.jd2.votetask.dto.PerformerDTO;

public interface IPerformersDao extends Dao<PerformerDTO> {
    boolean exist(Long id);

    void update(PerformerDTO performerDTO);
}
