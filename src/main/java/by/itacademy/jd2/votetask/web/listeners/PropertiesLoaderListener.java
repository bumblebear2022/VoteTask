package by.itacademy.jd2.votetask.web.listeners;


import by.itacademy.jd2.votetask.service.AutoMailService;
import by.itacademy.jd2.votetask.util.ApplicationContextHolder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class PropertiesLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContextHolder.getContext().getBean("AutoMailServiceBean", AutoMailService.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ApplicationContextHolder.getContext().getBean("AutoMailServiceBean", AutoMailService.class).stopProcess();
    }
}
