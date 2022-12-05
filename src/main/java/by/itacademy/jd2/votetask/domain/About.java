package by.itacademy.jd2.votetask.domain;

import java.time.LocalDateTime;

public class About {

    private final String text;

    private final LocalDateTime time;

    public About(String text, LocalDateTime time) {
        this.text = text;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
