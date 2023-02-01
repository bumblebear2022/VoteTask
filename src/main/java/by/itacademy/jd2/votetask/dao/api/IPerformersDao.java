package by.itacademy.jd2.votetask.dao.api;

import by.itacademy.jd2.votetask.domain.Performer;

public interface IPerformersDao extends Dao<Performer> {
    boolean exist(Long id);

    void update(Performer performer);
}
