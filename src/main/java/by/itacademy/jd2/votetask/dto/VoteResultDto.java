package by.itacademy.jd2.votetask.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class VoteResultDto {
    private final Map<String, Long> sortedPerformerVotes;
    private final Map<String, Long> sortedGenreVotes;
    private final Map<LocalDateTime, String> sortedVoteInfos;

    public VoteResultDto(Map<String, Long> sortedPerformerVotes, Map<String, Long> sortedGenreVotes, Map<LocalDateTime, String> sortedVoteInfos) {
        this.sortedPerformerVotes = sortedPerformerVotes;
        this.sortedGenreVotes = sortedGenreVotes;
        this.sortedVoteInfos = sortedVoteInfos;
    }

    public Map<String, Long> getSortedPerformerVotes() {
        return sortedPerformerVotes;
    }

    public Map<String, Long> getSortedGenreVotes() {
        return sortedGenreVotes;
    }

    public Map<LocalDateTime, String> getSortedVoteInfos() {
        return sortedVoteInfos;
    }
}
