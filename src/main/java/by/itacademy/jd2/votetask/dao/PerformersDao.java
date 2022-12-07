package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.domain.Performer;

import java.util.List;

public class PerformersDao implements IPerformersDao<Performer> {
    private static List<Performer> PERFORMERS = List.of(new Performer("Performer 1"),
            new Performer("Performer 2"), new Performer("Performer 3"), new Performer("Performer 4"));

    @Override
    public void create(Performer performer) {
        PERFORMERS.add(performer);
    }

    @Override
    public List<Performer> readAll() {
        return PERFORMERS;
    }

    @Override
    public void delete(Performer performer) {
       PERFORMERS.remove(performer);
    }
}
