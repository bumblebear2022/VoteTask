package by.itacademy.jd2.votetask.storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Switch {
    private static final String PATH = Objects.requireNonNull(Switch.class.getClassLoader()
            .getResource("properties.txt")).getPath();
    public static final String STARTMODE = "startmode";


    public static StorageOption getMode() {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File has been deleted" + e);
        }

        try {
            String readLine = reader.readLine();
            if (readLine == null) {
                throw new IOException();
            }
            String[] split = readLine.split("=");
            String startMode = split[0].toLowerCase();
            String mode = startMode.equals(STARTMODE) ? split[1].toUpperCase() : "";

            switch (mode) {
                case ("DB"):
                    return StorageOption.DATABASE;
                case ("MEMORY"):
                    return StorageOption.MEMORY;
                default:
                    throw new IllegalArgumentException();
            }

        } catch (IOException e) {
            throw new RuntimeException("Setting is absent: " + e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid settings: " + e);
        }
    }
}
