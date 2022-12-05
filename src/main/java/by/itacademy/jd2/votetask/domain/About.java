package by.itacademy.jd2.votetask.domain;

import java.time.LocalDateTime;

public class About {

    private String text;

    private LocalDateTime time;

    public About(String text, LocalDateTime time) {
        this.text = text;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
