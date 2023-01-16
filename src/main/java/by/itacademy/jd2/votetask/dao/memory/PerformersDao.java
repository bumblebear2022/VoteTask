package by.itacademy.jd2.votetask.dao.memory;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.factories.VoteDaoSingleton;
import by.itacademy.jd2.votetask.dto.PerformerDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformersDao implements IPerformersDao<PerformerDTO> {
    private final Map<Long, PerformerDTO> performers = new HashMap<>();

    private long idCounter = 4L;

    public PerformersDao() {
        performers.put(1L, new PerformerDTO(1L, "Performer 1"));
        performers.put(2L, new PerformerDTO(2L, "Performer 2"));
        performers.put(3L, new PerformerDTO(3L, "Performer 3"));
        performers.put(4L, new PerformerDTO(4L, "Performer 4"));
    }

    @Override
    public void create(PerformerDTO performerDTO) {
        PerformerDTO bufferedDTO = new PerformerDTO(createId(), performerDTO.getNickName());
        performers.put(bufferedDTO.getId(), bufferedDTO);
    }

    @Override
    public List<PerformerDTO> readAll() {
        return new ArrayList<>(performers.values());
    }

    @Override
    public void update(PerformerDTO performerDTO) {
        performers.put(performerDTO.getId(), performerDTO);
    }

    @Override
    public boolean delete(Long id) {
        if (VoteDaoSingleton.getInstance().isVotedPerformer(id)) {
            return false;
        } else {
            performers.remove(id);
            return true;
        }
    }

    @Override
    public boolean exist(Long id) {
        return performers.get(id) != null;
    }

    private long createId() {
        idCounter++;
        return idCounter;
    }
}
