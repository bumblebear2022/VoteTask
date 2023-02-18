package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.domain.SavedVote;
import by.itacademy.jd2.votetask.dto.VoteResultDto;
import by.itacademy.jd2.votetask.service.api.IGenreService;
import by.itacademy.jd2.votetask.service.api.IPerformerService;
import by.itacademy.jd2.votetask.service.api.IStatisticsService;
import by.itacademy.jd2.votetask.service.api.IVoteService;
import by.itacademy.jd2.votetask.util.SavedVoteComparatorByTime;
import by.itacademy.jd2.votetask.util.SortMapUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticsService implements IStatisticsService {

    private final IVoteService voteService;
    private final IPerformerService performerService;
    private final IGenreService genreService;

    public StatisticsService(IVoteService voteService, IPerformerService performerService, IGenreService genreService) {
        this.voteService = voteService;
        this.performerService = performerService;
        this.genreService = genreService;
    }

    public VoteResultDto getVoteResult() {
        List<SavedVote> voteDtoList = voteService.getVotes();
        Map<String, Long> sortedPerformerVotes = getSortedPerformerVotes(voteDtoList);
        Map<String, Long> sortedGenreVotes = getSortedGenreVotes(voteDtoList);
        List<SavedVote> sortedVoteInfos = getSortedVoteInfos(voteDtoList);
        return new VoteResultDto(sortedPerformerVotes, sortedGenreVotes, sortedVoteInfos);
    }


    private Map<String, Long> getSortedPerformerVotes(List<SavedVote> voteDtoList) {
        List<Performer> performers = performerService.getContent();
        Map<Long, String> performerNamesMap = performers.stream()
                .collect(Collectors.toMap(Performer::getId, Performer::getNickName));

        Map<Long, Long> idVotesForPerformers = voteDtoList.stream()
                .map(SavedVote::getVoiceForPerformer)
                .map(Performer::getId)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> nameVotesForPerformers = idVotesForPerformers.entrySet().stream()
                .collect(Collectors.toMap(entry -> performerNamesMap.get(entry.getKey()), Map.Entry::getValue));

        return SortMapUtil.sortByValue(nameVotesForPerformers);
    }

    private Map<String, Long> getSortedGenreVotes(List<SavedVote> voteDtoList) {
        List<Genre> genres = genreService.getContent();
        Map<Long, String> genresTitleMap = genres.stream()
                .collect(Collectors.toMap(Genre::getId, Genre::getTitle));

        Map<Long, Long> idVotesForGenres = voteDtoList.stream()
                .map(SavedVote::getVoicesForGenres)
                .flatMap(Collection::stream)
                .map(Genre::getId)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> nameVotesForPerformers = idVotesForGenres.entrySet().stream()
                .collect(Collectors.toMap(entry -> genresTitleMap.get(entry.getKey()), Map.Entry::getValue));

        return SortMapUtil.sortByValue(nameVotesForPerformers);
    }

    private List<SavedVote> getSortedVoteInfos(List<SavedVote> voteDtoList) {
        voteDtoList.sort(new SavedVoteComparatorByTime());
        return voteDtoList;
    }
}