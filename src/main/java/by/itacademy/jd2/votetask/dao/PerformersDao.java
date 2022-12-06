package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.data.PerformersList;
import by.itacademy.jd2.votetask.domain.Performer;

import java.util.List;

public class PerformersDao implements IPerformersDao<Performer> {
    @Override
    public List<Performer> readAll() {
        return PerformersList.getPERFORMERS();
    }

}
