package by.itacademy.jd2.votetask.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "emails")
public class Email {
    @Id
    @GeneratedValue(generator="increment" )
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "isSent")
    private boolean isSent;

    @Column(name = "sendingAttempts")
    private Long sendingAttempts;

    @Version
    @Column(name = "version")
    private Integer version;

    public Email() {
    }

    public Email(Long id) {
        this.id = id;
    }

    public Email(String email) {
        this.email = email;
        this.isSent = false;
        this.sendingAttempts = 0L;
    }

    public Email(Long id, String email) {
        this.id = id;
        this.email = email;
        this.isSent = false;
        this.sendingAttempts = 0L;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Long getSendingAttempts() {
        return sendingAttempts;
    }

    public  boolean getIsSent() {
        return isSent;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsSent(boolean isSent) {
        this.isSent = isSent;
    }

    public void setSendingAttempts(Long sendingAttempts) {
        this.sendingAttempts = sendingAttempts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return isSent == email1.isSent && Objects.equals(id, email1.id) && Objects.equals(email, email1.email) && Objects.equals(sendingAttempts, email1.sendingAttempts) && Objects.equals(version, email1.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, isSent, sendingAttempts, version);
    }
}
