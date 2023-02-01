package by.itacademy.jd2.votetask.web.listeners;

import by.itacademy.jd2.votetask.dao.database.datasource.DataSourceSingleton;
import by.itacademy.jd2.votetask.dao.database.hibernate.EntityManagerFactoryHolder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class PropertiesLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        File configDirectory = new File(System.getenv("/opt/tomcat/conf"));
        File propertiesSrc = new File("/opt/tomcat/conf" + "/application.properties");
        EntityManagerFactoryHolder.getInstance();
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(propertiesSrc));
            DataSourceSingleton.setProperties(properties);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Property file not found, please create file 'application.properties' in conf directory", e);
        } catch (IOException e) {
            throw new RuntimeException("Reading application.properties file error", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DataSourceSingleton.close();
    }
}
