package by.itacademy.jd2.votetask.domain;

import java.util.List;

public class Vote {

    private String performer;

    private List<String> genres;

    private String info;

    public Vote(String performer, List<String> genres, String info) {
        this.performer = performer;
        this.genres = genres;
        this.info = info;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
