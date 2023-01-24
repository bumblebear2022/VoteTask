package by.itacademy.jd2.votetask.dao.database.datasource;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceHolder implements IDataSourceHolder {

    public static final String DRIVER_CLASS_PROPERTY_NAME = "dataSource.drivenClassName";
    public static final String USERNAME_PROPERTY_NAME = "dataSource.username";
    public static final String PASSWORD_PROPERTY_NAME = "dataSource.password";
    public static final String URL_PROPERTY_NAME = "dataSource.url";
    BasicDataSource basicDataSource;
    public DataSourceHolder(Properties properties) {
        basicDataSource = new BasicDataSource();

        String username = properties.getProperty(USERNAME_PROPERTY_NAME);
        String password = properties.getProperty(PASSWORD_PROPERTY_NAME);
        String drivenClassName = properties.getProperty(DRIVER_CLASS_PROPERTY_NAME);
        String url = properties.getProperty(URL_PROPERTY_NAME);

        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setDriverClassName(drivenClassName);
        basicDataSource.setUrl(url);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    @Override
    public void close() throws Exception {
            basicDataSource.close();
    }
}
