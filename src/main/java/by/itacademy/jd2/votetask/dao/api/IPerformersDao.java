package by.itacademy.jd2.votetask.dao.api;

import by.itacademy.jd2.votetask.dto.Performer;

public interface IPerformersDao extends Dao<Performer> {
    boolean exist(Long id);

    void update(Performer performer);
}
