package by.itacademy.jd2.votetask.dao;

import java.util.List;

public interface Dao<T> {
    List<T> readAll();
}
