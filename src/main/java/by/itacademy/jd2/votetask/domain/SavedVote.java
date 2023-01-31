package by.itacademy.jd2.votetask.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table( name = "data.votes" )
public class SavedVote {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    private LocalDateTime createDateTime;
    @OneToMany
    private Long voiceForPerformer;
    @ManyToMany
    private List<Long> voicesForGenres;
    private String about;
    private String email;

    public SavedVote() {
    }

    public SavedVote(Long id, Long voiceForPerformer, List<Long> voicesForGenres, String about, String email) {
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

    public Long getVoiceForPerformer() {
        return voiceForPerformer;
    }

    public void setVoiceForPerformer(Long voiceForPerformer) {
        this.voiceForPerformer = voiceForPerformer;
    }

    public List<Long> getVoicesForGenres() {
        return voicesForGenres;
    }

    public void setVoicesForGenres(List<Long> voicesForGenres) {
        this.voicesForGenres = voicesForGenres;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
