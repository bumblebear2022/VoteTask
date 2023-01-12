package by.itacademy.jd2.votetask.dto;

import java.util.Objects;

public class CrossPerformerDto {
    private final Long voteId;
    private final Long performerId;

    public CrossPerformerDto(Long voteId, Long performerId) {
        this.voteId = voteId;
        this.performerId = performerId;
    }

    public Long getVoteId() {
        return voteId;
    }

    public Long getPerformerId() {
        return performerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrossPerformerDto that = (CrossPerformerDto) o;
        return Objects.equals(voteId, that.voteId) && Objects.equals(performerId, that.performerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteId, performerId);
    }
}
