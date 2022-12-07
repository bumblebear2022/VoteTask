package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.IPerformersDao;
import by.itacademy.jd2.votetask.domain.Performer;

import java.util.List;
import java.util.stream.Collectors;

public class PerformerService implements IPerformerService {


    private final IPerformersDao<Performer> performersDao;

    public PerformerService(IPerformersDao<Performer> performersDao) {
        this.performersDao = performersDao;
    }

    @Override
    public List<String> getContent() {
        return performersDao.readAll().stream()
                .map(Performer::getNickName)
                .collect(Collectors.toList());
    }
}
