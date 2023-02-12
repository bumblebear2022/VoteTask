package by.itacademy.jd2.votetask.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "votes")
public class SavedVote {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Column(name = "date_time")
    private LocalDateTime createDateTime;
    @Column(name = "about")
    private String about;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "vote_performer", joinColumns = @JoinColumn(name = "id_vote"), inverseJoinColumns = @JoinColumn(name = "id_performer"))
    private Performer voiceForPerformer;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vote_genre", joinColumns = @JoinColumn(name = "id_vote"), inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private List<Genre> voicesForGenres;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "vote_email", joinColumns = @JoinColumn(name = "id_vote"), inverseJoinColumns = @JoinColumn(name = "id_email"))
    private Email email;


    public SavedVote() {
    }

    public SavedVote(Long id, Performer voiceForPerformer, List<Genre> voicesForGenres, String about, Email email) {
        this.id = id;
        this.createDateTime = LocalDateTime.now();
        this.voiceForPerformer = voiceForPerformer;
        this.voicesForGenres = voicesForGenres;
        this.about = about;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Performer getVoiceForPerformer() {
        return voiceForPerformer;
    }

    public void setVoiceForPerformer(Performer voiceForPerformer) {
        this.voiceForPerformer = voiceForPerformer;
    }

    public List<Genre> getVoicesForGenres() {
        return voicesForGenres;
    }

    public void setVoicesForGenres(List<Genre> voicesForGenres) {
        this.voicesForGenres = voicesForGenres;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email.getEmail();
    }

    public void setEmail(String email) {
        this.email.setEmail(email);
    }

    public boolean getIsSent() {
        return email.getIsSent();
    }

    public void setIsSent(boolean sent) {
         email.setIsSent(sent);
    }

    public void setSendingAttempts(Long sendingAttempts) {
        email.setSendingAttempts(sendingAttempts);
    }

    public Long getSendingAttempts() {
        return email.getSendingAttempts();
    }
}
