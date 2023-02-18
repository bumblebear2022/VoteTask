package by.itacademy.jd2.votetask.dto;

import java.util.Objects;

public class GenreDto {
    private Long id;
    private String title;

    public GenreDto() {
    }

    public GenreDto(String title) {
        this.title = title;
    }

    public GenreDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

}
