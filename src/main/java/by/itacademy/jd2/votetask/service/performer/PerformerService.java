package by.itacademy.jd2.votetask.service.performer;

import by.itacademy.jd2.votetask.content.IPerformersDao;
import by.itacademy.jd2.votetask.content.PerformersContentHolder;
import by.itacademy.jd2.votetask.domain.Performer;

import java.util.List;
import java.util.stream.Collectors;

public class PerformerService implements IPerformerService {
    private static final String HEADER = "<p><b>Choose one performer:</b></p>";

    private static final String FOOTER = "<p><b>Thanks for your vote!</b></p>";

    private final IPerformersDao<Performer> performersDao = new PerformersContentHolder();

    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public String getFooter() {
        return FOOTER;
    }


    @Override
    public List<String> getContent() {
        return performersDao.readAll().stream()
                .map(Performer::getNickName)
                .collect(Collectors.toList());
    }
}
