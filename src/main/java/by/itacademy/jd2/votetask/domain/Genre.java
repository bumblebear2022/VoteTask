package by.itacademy.jd2.votetask.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(generator="increment" )
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    @Column(name = "name")
    private String title;

    @Version
    @Column(name = "version")
    private Integer version;
    public Genre() {
    }
    public Genre(Long id) {
        this.id = id;
    }

    public Genre(String title) {
        this.title = title;
    }

    public Genre(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Genre(Long id, String title, Integer version) {
        this.id = id;
        this.title = title;
        this.version = version;
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
        Genre genre = (Genre) o;
        return Objects.equals(title, genre.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "GenreDTO{" +
                "title='" + title + '\'' +
                '}';
    }
}
