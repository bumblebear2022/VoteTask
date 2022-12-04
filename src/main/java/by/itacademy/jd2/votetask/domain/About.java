package by.itacademy.jd2.votetask.domain;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;

public class About {
    private static About INSTANCE;

    Map<LocalTime,String> about = new TreeMap<>();

    public static About getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new About();
        }
        return INSTANCE;
    }

    public void addAbout(LocalTime time, String about){
        LocalTime timeWithoutMillis = time.truncatedTo(ChronoUnit.SECONDS);
        this.about.put(timeWithoutMillis, about);
    }

    public Map<LocalTime,String> getAbout() {
        return about;
    }
}
