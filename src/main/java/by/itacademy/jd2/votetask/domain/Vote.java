package by.itacademy.jd2.votetask.domain;

import java.util.List;

public class Vote {

    private final String voiceForPerformer;

    private final List<String> voicesForGenres;

    private final String info;

    public Vote(String performer, List<String> voicesForGenres, String info) {
        this.voiceForPerformer = performer;
        this.voicesForGenres = voicesForGenres;
        this.info = info;
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
}
