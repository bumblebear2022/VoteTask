package by.itacademy.jd2.votetask.dao.datasource;

import by.itacademy.jd2.votetask.service.factories.PerformerServiceSingleton;

import java.util.Properties;

public class DataSourceSingleton {
    private static Properties properties;
    private static IDataSourceHolder INSTANCE;

    private DataSourceSingleton() {
    }

    public static void setProperties(Properties properties){
        synchronized (DataSourceSingleton.class){
            if(INSTANCE != null){
                throw new IllegalStateException("Settings cannot be changed when connection has already been created");
            }
            DataSourceSingleton.properties = properties;
        }
    }

    public static IDataSourceHolder getInstance() {
        if(INSTANCE == null){
            synchronized (PerformerServiceSingleton.class){
                if (INSTANCE == null) {
                    INSTANCE = new DataSourceHolder(properties);
                }
            }
        }
        return INSTANCE;
    }

    public static void close(){
        try {
            INSTANCE.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
