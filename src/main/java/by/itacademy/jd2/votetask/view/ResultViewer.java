package by.itacademy.jd2.votetask.view;

import by.itacademy.jd2.votetask.dto.VoteResultDto;
import by.itacademy.jd2.votetask.service.StatisticsService;
import by.itacademy.jd2.votetask.service.factories.StatisticsServiceSingleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ResultViewer {
    private final StatisticsService statisticsService = StatisticsServiceSingleton.getInstance();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.LLL HH:mm:ss");

    public String getView(){
        VoteResultDto voteResult = statisticsService.getVoteResult();
        return doWritePerformers(voteResult) + doWriteGenres(voteResult) + doWriteInfos(voteResult);
    }

    private String doWritePerformers(VoteResultDto voteResult){
        String s = "<p>Total score among Performers:</p>";
        Map<String, Long> performers = voteResult.getSortedPerformerVotes();
        for (Map.Entry<String, Long> performer : performers.entrySet()) {
           s = s + "<p>" + performer.getKey() + "  " + performer.getValue() + "</p>";
        }
        return s;
    }
    private String doWriteGenres(VoteResultDto voteResult){
        String s = "<p>Total score among Genres:</p>";
        Map<String, Long> genres = voteResult.getSortedPerformerVotes();
        for (Map.Entry<String, Long> genre : genres.entrySet()) {
            s = s + "<p>" + genre.getKey() + "  " + genre.getValue() + "</p>";
        }
        return s;
    }
    private String doWriteInfos(VoteResultDto voteResult){
        String s = "<p>Info about voters:</p>";
        Map<LocalDateTime, String> infos = voteResult.getSortedVoteInfos();
        for (Map.Entry<LocalDateTime, String> info : infos.entrySet()) {
            s = s + "<p>" + info.getKey().format(FORMATTER) + "  " + info.getValue() + "</p>";
        }
        return s;
    }

}
