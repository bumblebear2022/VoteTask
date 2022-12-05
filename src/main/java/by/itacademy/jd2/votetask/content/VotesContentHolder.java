package by.itacademy.jd2.votetask.content;

import by.itacademy.jd2.votetask.domain.About;
import by.itacademy.jd2.votetask.domain.Vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotesContentHolder {

    public VotesContentHolder() {
        initPerformerVotes();
        initGenreVotes();
    }

    private Map<String, Integer> performerVotes = new HashMap<>();

    private Map<String, Integer> genreVotes = new HashMap<>();

    private List<About> voteInfo = new ArrayList<>();

    public void performerVoteIncrement(Vote vote) {
        String performer = vote.getPerformer();
        Integer currentVotes = performerVotes.get(performer);
        if (currentVotes != null) {
            performerVotes.put(performer,currentVotes+1);
        }
    }

    public void genreVoteIncrement(Vote vote) {

    }

    public void addVoteInfo(Vote vote) {

    }

    private void initPerformerVotes() {
        performerVotes.put("Performer 1", 0);
        performerVotes.put("Performer 2", 0);
        performerVotes.put("Performer 3", 0);
        performerVotes.put("Performer 4", 0);
    }

    private void initGenreVotes() {
        genreVotes.put("Genre 1", 0);
        genreVotes.put("Genre 2", 0);
        genreVotes.put("Genre 3", 0);
        genreVotes.put("Genre 4", 0);
        genreVotes.put("Genre 5", 0);
        genreVotes.put("Genre 6", 0);
        genreVotes.put("Genre 7", 0);
        genreVotes.put("Genre 8", 0);
        genreVotes.put("Genre 9", 0);
        genreVotes.put("Genre 10", 0);

    }

}
