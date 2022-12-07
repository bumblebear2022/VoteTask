package by.itacademy.jd2.votetask.dto;

import java.util.List;

public class VoteDto {

    private final String voiceForPerformer;

    private final List<String> voicesForGenres;

    private final String info;

    public VoteDto(String voiceForPerformer, List<String> voicesForGenres, String info) {
        this.voiceForPerformer = voiceForPerformer;
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
