package by.itacademy.jd2.votetask.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Vote {

    private final String voiceForPerformer;

    private final List<String> voicesForGenres;

    private final String info;
    private final LocalDateTime time;

    public Vote(String voiceForPerformer, List<String> voicesForGenres, String info, LocalDateTime time) {
        this.voiceForPerformer = voiceForPerformer;
        this.voicesForGenres = voicesForGenres;
        this.info = info;
        this.time = time;
    }

    public String getVoiceForPerformer() {
        return voiceForPerformer;
    }

    public List<String> getVoicesForGenres() {
        return voicesForGenres;
    }

    public String getInfo() {
        return info;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
