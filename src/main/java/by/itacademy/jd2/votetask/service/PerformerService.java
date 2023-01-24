package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dto.PerformerDTO;
import by.itacademy.jd2.votetask.service.api.IPerformerService;

import java.util.List;

public class PerformerService implements IPerformerService {


    private final IPerformersDao performersDao;


    public PerformerService(IPerformersDao performersDao) {
        this.performersDao = performersDao;
    }

    @Override
    public List<PerformerDTO> getContent() {
        return performersDao.readAll();
    }

    @Override
    public void create(PerformerDTO performerDTO) {
        performersDao.create(performerDTO);
    }

    @Override
    public void update(PerformerDTO performerDTO){
        performersDao.update(performerDTO);
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

    public List<PerformerDTO> getPerformers() {
        return performersDao.readAll();
    }
}
