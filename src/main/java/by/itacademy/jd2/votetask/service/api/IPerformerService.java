package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.dto.PerformerDto;

import java.util.List;

public interface IPerformerService {
    List<Performer> getContent();

    void create(PerformerDto performerDto);

    void update(Long id, Integer version, PerformerDto performerDto);

    boolean delete(Long id);

    boolean exist(Long id);

    List<Performer> getPerformers();
}
