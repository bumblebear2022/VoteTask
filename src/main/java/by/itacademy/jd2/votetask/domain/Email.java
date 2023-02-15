package by.itacademy.jd2.votetask.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
}
