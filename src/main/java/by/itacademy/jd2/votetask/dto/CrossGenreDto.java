package by.itacademy.jd2.votetask.dto;

import java.util.Objects;

public class CrossGenreDto {
    private final Long voteId;
    private final Long genreId;

    public CrossGenreDto(Long voteId, Long genreId) {
        this.voteId = voteId;
        this.genreId = genreId;
    }

    public Long getVoteId() {
        return voteId;
    }

    public Long getGenreId() {
        return genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrossGenreDto that = (CrossGenreDto) o;
        return Objects.equals(voteId, that.voteId) && Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteId, genreId);
    }

    @Override
    public String toString() {
        return "CrossGenreDto{" +
                "voteId=" + voteId +
                ", genreId=" + genreId +
                '}';
    }
}
