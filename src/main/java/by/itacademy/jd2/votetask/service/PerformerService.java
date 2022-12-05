package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.content.IPerformersDao;
import by.itacademy.jd2.votetask.content.PerformersContentHolder;

import java.util.List;

public class PerformerService implements IPerformerService {
    public static final String HEADER = "<p><b>Choose one performer:</b></p>";

    private final IPerformersDao iPerformersDao = new PerformersContentHolder();

    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public List<String> getContent() {
        return iPerformersDao.readAll();
    }
}
