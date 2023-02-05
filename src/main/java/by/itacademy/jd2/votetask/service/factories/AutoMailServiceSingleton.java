package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.service.AutoMailService;
import by.itacademy.jd2.votetask.service.api.IAutoMailService;

public class AutoMailServiceSingleton {

    private volatile static IAutoMailService instance;

    private AutoMailServiceSingleton() {
    }

    public static IAutoMailService getInstance() {
        if (instance == null) {
            synchronized (AutoMailServiceSingleton.class) {
                if (instance == null) {
                    instance = new AutoMailService();
                }
            }
        }
        return instance;
    }
}
