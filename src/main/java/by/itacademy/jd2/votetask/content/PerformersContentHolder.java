package by.itacademy.jd2.votetask.content;

import by.itacademy.jd2.votetask.domain.Performer;

import java.util.List;

public class PerformersContentHolder implements IPerformersDao<Performer> {

    private static final List<Performer> PERFORMERS = List.of(new Performer("Performer 1"),
            new Performer("Performer 2"), new Performer("Performer 3"), new Performer("Performer 4"));

    @Override
    public List<Performer> readAll() {
        return PERFORMERS;
    }


}
