package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.dto.PerformerDto;
import by.itacademy.jd2.votetask.service.api.IPerformerService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PerformerService implements IPerformerService {


    private final IPerformersDao performersDao;


    public PerformerService(IPerformersDao performersDao) {
        this.performersDao = performersDao;
    }

    @Override
    @Transactional
    public List<Performer> getContent() {
        return performersDao.readAll();
    }

    @Override
    @Transactional
    public PerformerDto getById(Long id) {
        Performer performer = performersDao.getById(id);
        return new PerformerDto(performer.getId(),performer.getNickName());
    }

    @Override
    @Transactional
    public void create(PerformerDto performerDto) {
        String nickName = performerDto.getNickName();
        performersDao.create(new Performer(nickName));
    }

    @Override
    @Transactional
    public void update(Long id, Integer version, PerformerDto performerDto) {
        String nickName = performerDto.getNickName();
        performersDao.update(new Performer(id,nickName,version));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return performersDao.delete(id);
    }

    @Override
    @Transactional
    public boolean exist(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Performer nickname can't be empty");
        }
        return performersDao.exist(id);
    }
}
