package by.itacademy.jd2.votetask.dao.memory;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.database.factories.VoteDatabaseDaoSingleton;
import by.itacademy.jd2.votetask.domain.Performer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformersMemoryDao implements IPerformersDao {
    private final Map<Long, Performer> performers = new HashMap<>();

    private long idCounter = 4L;

    public PerformersMemoryDao() {
        performers.put(1L, new Performer(1L, "Performer 1"));
        performers.put(2L, new Performer(2L, "Performer 2"));
        performers.put(3L, new Performer(3L, "Performer 3"));
        performers.put(4L, new Performer(4L, "Performer 4"));
    }

    @Override
    public void create(Performer performer) {
        Performer bufferedDTO = new Performer(createId(), performer.getNickName());
        performers.put(bufferedDTO.getId(), bufferedDTO);
    }

    @Override
    public List<Performer> readAll() {
        return new ArrayList<>(performers.values());
    }

    @Override
    public void update(Performer performer) {
        performers.put(performer.getId(), performer);
    }

    @Override
    public boolean delete(Long id) {
        if (VoteDatabaseDaoSingleton.getInstance().isVotedPerformer(id)) {
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
