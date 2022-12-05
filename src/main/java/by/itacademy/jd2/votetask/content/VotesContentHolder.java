package by.itacademy.jd2.votetask.content;

import by.itacademy.jd2.votetask.domain.About;
import by.itacademy.jd2.votetask.domain.Vote;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotesContentHolder {

    private static VotesContentHolder INSTANCE;
    private VotesContentHolder() {
        initPerformerVotes();
        initGenreVotes();
    }

    private final Map<String, Integer> performerVotes = new HashMap<>();

    private final Map<String, Integer> genreVotes = new HashMap<>();

    private final List<About> voteInfos = new ArrayList<>();

    public static VotesContentHolder getInstance(){
        if(INSTANCE == null){
            INSTANCE = new VotesContentHolder();
        }
        return INSTANCE;
    }

    public void performerVoteIncrement(Vote vote) {
        String performer = vote.getPerformer();
        Integer currentVotes = performerVotes.get(performer);
        if (currentVotes != null) {
            performerVotes.put(performer, currentVotes + 1);
        }
    }

    public void genreVoteIncrement(Vote vote) {
        List<String> genres = vote.getGenres();
        for (String genre : genres) {
            Integer currentVotes = genreVotes.get(genre);
            if (currentVotes != null) {
                genreVotes.put(genre, currentVotes + 1);
            }
        }
    }

    public void addVoteInfo(Vote vote) {
        String info = vote.getInfo();
        if (info != null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            About about = new About(info, localDateTime);
            voteInfos.add(about);
        }
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

    public Map<String, Integer> getPerformerVotes() {
        return performerVotes;
    }

    public Map<String, Integer> getGenreVotes() {
        return genreVotes;
    }

    public List<About> getVoteInfos() {
        return voteInfos;
    }
}
