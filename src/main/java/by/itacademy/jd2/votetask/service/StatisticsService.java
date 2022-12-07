package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.IVoteDao;
import by.itacademy.jd2.votetask.dao.VoteDao;
import by.itacademy.jd2.votetask.domain.About;
import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.util.SortMapUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService implements IStatisticsService {

    private final IVoteDao<Vote> voteDao = new VoteDao();

    private final Map<String, Integer> votesForPerformers = new HashMap<>();

    private final Map<String, Integer> votesForGenres = new HashMap<>();

    private final List<About> voteTexts = new ArrayList<>();


    public StatisticsService() {
        List<Vote> voteList = voteDao.readAll();
        for (Vote vote : voteList) {
            performerVoteIncrement(vote);
            genreVoteIncrement(vote);
            addVoteInfo(vote);
        }
    }

    public void performerVoteIncrement(Vote vote) {
        String performer = vote.getVoiceForPerformer();
        Integer currentVotes = votesForPerformers.get(performer);
        if (currentVotes != null) {
            votesForPerformers.put(performer, currentVotes + 1);
        }
    }

    public void genreVoteIncrement(Vote vote) {
        List<String> genres = vote.getVoicesForGenres();
        for (String genre : genres) {
            Integer currentVotes = votesForGenres.get(genre);
            if (currentVotes != null) {
                votesForGenres.put(genre, currentVotes + 1);
            }
        }
    }

    public void addVoteInfo(Vote vote) {
        String info = vote.getInfo();
        if (info != null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            About about = new About(info, localDateTime);
            voteTexts.add(about);
        }
    }

    public Map<String, Integer> getSortedPerformerVotes() {
        return SortMapUtil.sortMap(votesForPerformers);
    }

    public Map<String, Integer> getSortedGenreVotes() {
        return SortMapUtil.sortMap(votesForGenres);

    }

    public List<About> getSortedVoteInfos() {
        Collections.sort(voteTexts);
        return voteTexts;
    }

}
