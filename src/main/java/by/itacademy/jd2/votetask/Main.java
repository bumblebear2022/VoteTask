package by.itacademy.jd2.votetask;

import by.itacademy.jd2.votetask.dto.CrossGenreDto;
import by.itacademy.jd2.votetask.dto.CrossPerformerDto;
import by.itacademy.jd2.votetask.dto.MainVoteDto;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.dto.VoteDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<MainVoteDto> baseVotes = List.of(new MainVoteDto(1L, LocalDateTime.now(), "about1"));
        List<CrossPerformerDto> votesForPerformers = List.of(new CrossPerformerDto(1L, 2L));
        List<CrossGenreDto> votesForGenres = List.of(new CrossGenreDto(1L, 3L),
                new CrossGenreDto(1L, 4L),
                new CrossGenreDto(1L, 5L));

        Map<Long, List<Long>> collectPerformers = votesForPerformers.stream()
                .collect(Collectors.groupingBy(CrossPerformerDto::getVoteId,
                        Collectors.mapping(CrossPerformerDto::getPerformerId, Collectors.toList())));

        Map<Long, List<Long>> collectGenres = votesForGenres.stream()
                .collect(Collectors.groupingBy(CrossGenreDto::getVoteId,
                        Collectors.mapping(CrossGenreDto::getGenreId, Collectors.toList())));

        List<SavedVoteDTO> savedVoteDTOS = new ArrayList<>();

        for (MainVoteDto vote : baseVotes) {
            Long id = vote.getId();
            LocalDateTime createDateTime = vote.getCreateDateTime();
            Long voteForPerformer = collectPerformers.get(id).get(0);
            List<Long> votesForGenresList = collectGenres.get(id);

            VoteDto voteDto = new VoteDto(voteForPerformer, votesForGenresList, vote.getAbout());
            SavedVoteDTO savedVoteDTO = new SavedVoteDTO(id,createDateTime, voteDto);
            savedVoteDTOS.add(savedVoteDTO);
        }
        System.out.println(savedVoteDTOS.get(0));
    }
}
