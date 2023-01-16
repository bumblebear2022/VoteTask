package by.itacademy.jd2.votetask.provider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Switch {

    private static final String PATH = "VoteTaskGroup2\\properties.txt";

    //to switch storage change return StorageOption
    public static StorageOption getMode() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл параметров приложения был удален");
        }
        String mode;
        try {
            if ((mode = reader.readLine()) == null) {
                throw new IOException();
            }
            if (mode.startsWith("StartMode=\"") && mode.endsWith("\"")) {
                mode = mode.substring(10, mode.length() - 1).toUpperCase();
            } else {
                throw new IllegalArgumentException();
            }
            switch (mode) {
                case ("DB") :
                    return StorageOption.DATABASE;
                case ("MEMORY")  :
                    return StorageOption.MEMORY;
                default :
                    throw new IllegalArgumentException();
            }

        } catch (IOException e) {
            throw new RuntimeException("Отсутствуют настройки запуска");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Неправильно заданы параметры запуска");
        }
    }
}
