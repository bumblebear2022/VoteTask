package by.itacademy.jd2.votetask.dto;

import java.util.Objects;

public class PerformerDto {
    private  Long id;
    private  String nickName;

    public PerformerDto(Long id, String nickName) {
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
        PerformerDto that = (PerformerDto) o;
        return Objects.equals(id, that.id) && Objects.equals(nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName);
    }

    @Override
    public String toString() {
        return "PerformerDto{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
