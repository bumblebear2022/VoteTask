package by.itacademy.jd2.votetask.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class SavedVoteDTO {

    private Long id;
    private LocalDateTime createDateTime;
    private final VoteDto vote;


    public SavedVoteDTO(Long id, VoteDto vote) {
        this.id = id;
        this.createDateTime = createDateTime;
        this.vote = vote;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public Long getId() {
        return id;
    }

    public VoteDto getVote() {
        return vote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedVoteDTO that = (SavedVoteDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(createDateTime, that.createDateTime) && Objects.equals(vote, that.vote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDateTime, vote);
    }

    @Override
    public String toString() {
        return "SavedVoteDTO{" +
                "id=" + id +
                ", createDateTime=" + createDateTime +
                ", vote=" + vote +
                '}';
    }
}
