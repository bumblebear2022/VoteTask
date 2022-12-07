package by.itacademy.jd2.votetask.domain;

import java.time.LocalDateTime;

public class About implements Comparable<About> {

    private final String info;
    private final LocalDateTime time;

    public About(String info, LocalDateTime time) {
        this.info = info;
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public int compareTo(About about) {
        return getTime().compareTo(about.getTime());
    }
}
