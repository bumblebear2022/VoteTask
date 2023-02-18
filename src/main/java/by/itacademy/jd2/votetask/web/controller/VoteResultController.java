package by.itacademy.jd2.votetask.web.controller;

import by.itacademy.jd2.votetask.dto.VoteResultDto;
import by.itacademy.jd2.votetask.service.api.IStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote-result")
public class VoteResultController {
    private final IStatisticsService statisticsService;

    public VoteResultController(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public VoteResultDto getVoteResult() {
        return statisticsService.getVoteResult();
    }
}