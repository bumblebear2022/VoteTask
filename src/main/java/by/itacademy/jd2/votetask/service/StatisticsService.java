package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.factories.VoteDaoSingleton;
import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.dto.VoteResultDto;
import by.itacademy.jd2.votetask.service.api.IStatisticsService;
import by.itacademy.jd2.votetask.util.SortMapUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticsService implements IStatisticsService {

    private final IVoteDao<Vote> voteDao = VoteDaoSingleton.getInstance();

    public VoteResultDto getVoteResult() {
        List<Vote> votes = voteDao.readAll();
        Map<String, Long> sortedPerformerVotes = getSortedPerformerVotes(votes);
        Map<String, Long> sortedGenreVotes = getSortedGenreVotes(votes);
        Map<LocalDateTime, String> sortedVoteInfos = getSortedVoteInfos(votes);
        return new VoteResultDto(sortedPerformerVotes, sortedGenreVotes, sortedVoteInfos);
    }


    private Map<String, Long> getSortedPerformerVotes(List<Vote> votes) {
        Map<String, Long> votesForPerformers = votes.stream()
                .collect(Collectors.groupingBy(Vote::getVoiceForPerformer, Collectors.counting()));

        return SortMapUtil.sortByValue(votesForPerformers);
    }

    private Map<String, Long> getSortedGenreVotes(List<Vote> votes) {
        Map<String, Long> votesForGenres = votes.stream()
                .map(Vote::getVoicesForGenres)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return SortMapUtil.sortByValue(votesForGenres);
    }

    private Map<LocalDateTime, String> getSortedVoteInfos(List<Vote> votes) {
        return votes.stream()
                .collect(Collectors.toMap(Vote::getTime, Vote::getInfo, (a, b) -> a, TreeMap::new));
    }

}
