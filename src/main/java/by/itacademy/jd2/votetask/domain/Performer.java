package by.itacademy.jd2.votetask.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;

@Entity
@Table( name = "performers")
public class Performer {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private  Long id;
    @Column(name = "name")
    private  String nickName;

    @Version
    @Column(name = "version")
    private Integer version;

    public Performer() {
    }
    public Performer(Long id) {
        this.id = id;
    }

    public Performer(String nickName) {
        this.nickName = nickName;
    }

    public Performer(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public Performer(Long id, String nickName, Integer version) {
        this.id = id;
        this.nickName = nickName;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Performer performer = (Performer) o;
        return Objects.equals(id, performer.id) && Objects.equals(nickName, performer.nickName) && Objects.equals(version, performer.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, version);
    }
}
