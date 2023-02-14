package by.itacademy.jd2.votetask.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MailDataSourceHolder {

    public static Map<String, String> getMailData() {

        Properties properties = getProperties();
        Map<String, String> mailData = new HashMap<>();
        String username = properties.getProperty("email.username");
        String password = properties.getProperty("email.password");

        mailData.put("username", username);
        mailData.put("password", password);
        return mailData;
    }

    private static Properties getProperties() {
        try (InputStream input = MailDataSourceHolder.class
                .getClassLoader()
                .getResourceAsStream("credential.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
