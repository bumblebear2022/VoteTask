package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.dto.PerformerDTO;

import java.util.List;

public interface IPerformerService {
    List<PerformerDTO> getContent();

    void create(PerformerDTO performerDTO);

    void update(PerformerDTO performerDTO);

    void delete(PerformerDTO performerDTO);

    boolean exist(Long id);
}
