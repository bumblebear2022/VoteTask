package by.itacademy.jd2.votetask.dao.api;

import java.util.List;

public interface Dao<T> {

    void create(T t);

    List<T> readAll();

    boolean delete(T t);
}
