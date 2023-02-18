package by.itacademy.jd2.votetask.dto;

import by.itacademy.jd2.votetask.domain.SavedVote;

import java.util.List;
import java.util.Map;

public class VoteResultDto {
    private final Map<String, Long> sortedPerformerVotes;
    private final Map<String, Long> sortedGenreVotes;
    private final List<SavedVote> sortedVoteInfos;

    public VoteResultDto(Map<String, Long> sortedPerformerVotes,
                         Map<String, Long> sortedGenreVotes,
                         List<SavedVote> sortedVoteInfos) {
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

    public List<SavedVote> getSortedVoteInfos() {
        return sortedVoteInfos;
    }
}
