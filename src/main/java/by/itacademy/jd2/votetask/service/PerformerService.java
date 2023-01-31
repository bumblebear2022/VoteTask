package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dto.Performer;
import by.itacademy.jd2.votetask.service.api.IPerformerService;

import java.util.List;

public class PerformerService implements IPerformerService {


    private final IPerformersDao performersDao;


    public PerformerService(IPerformersDao performersDao) {
        this.performersDao = performersDao;
    }

    @Override
    public List<Performer> getContent() {
        return performersDao.readAll();
    }

    @Override
    public void create(Performer performer) {
        performersDao.create(performer);
    }

    @Override
    public void update(Performer performer){
        performersDao.update(performer);
    }


    @Override
    public boolean delete(Long id) {
        return performersDao.delete(id);
    }

    public boolean exist(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Performer nickname can't be empty");
        }
        return performersDao.exist(id);
    }

    public List<Performer> getPerformers() {
        return performersDao.readAll();
    }
}
