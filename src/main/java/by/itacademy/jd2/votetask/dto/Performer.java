package by.itacademy.jd2.votetask.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
@Entity
@Table( name = "data.performers" )
public class Performer {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private  Long id;
    private  String nickName;

    public Performer() {
    }

    public Performer(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
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
        Performer that = (Performer) o;
        return Objects.equals(nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName);
    }

    @Override
    public String toString() {
        return "PerformerDTO{" +
                "nickName='" + nickName + '\'' +
                '}';
    }
}
