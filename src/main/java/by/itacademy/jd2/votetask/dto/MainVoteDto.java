package by.itacademy.jd2.votetask.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class MainVoteDto {
    private final Long id;
    private final LocalDateTime createDateTime;
    private final String about;

    public MainVoteDto(Long id, LocalDateTime createDateTime, String about) {
        this.id = id;
        this.createDateTime = createDateTime;
        this.about = about;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public String getAbout() {
        return about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainVoteDto that = (MainVoteDto) o;
        return Objects.equals(id, that.id) && Objects.equals(createDateTime, that.createDateTime) && Objects.equals(about, that.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDateTime, about);
    }
}
