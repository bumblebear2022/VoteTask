package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.dto.Performer;

import java.util.List;

public interface IPerformerService {
    List<Performer> getContent();

    void create(Performer performer);

    void update(Performer performer);

    boolean delete(Long id);

    boolean exist(Long id);

    List<Performer> getPerformers();
}
