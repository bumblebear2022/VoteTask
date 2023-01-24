package by.itacademy.jd2.votetask.dao.database.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDataSourceHolder extends AutoCloseable{
    Connection getConnection() throws SQLException;

}
