package by.itacademy.jd2.votetask.dto;

import java.util.Objects;

public class GenreDto {
    private final Long id;
    private final String title;

    public GenreDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDto genreDto = (GenreDto) o;
        return Objects.equals(id, genreDto.id) && Objects.equals(title, genreDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "GenreDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
