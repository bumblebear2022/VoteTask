package by.itacademy.jd2.votetask.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextHolder {

    private volatile static ApplicationContext instance;

    private ApplicationContextHolder() {
    }

    public static ApplicationContext getContext() {
        if (instance == null) {
            synchronized (ApplicationContextHolder.class) {
                if (instance == null) {
                    instance = new ClassPathXmlApplicationContext("dao-context.xml", "hibernate-context.xml", "service-context.xml");
                }
            }
        }
        return instance;
    }
}
