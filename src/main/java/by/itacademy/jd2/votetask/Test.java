package by.itacademy.jd2.votetask;

import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.service.VoteService;
import by.itacademy.jd2.votetask.util.ApplicationContextHolder;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        VoteService voteService = ApplicationContextHolder.getContext().getBean("VoteServiceBean", VoteService.class);
        List<Long> genres = new ArrayList<>();
        genres.add(1L);
        genres.add(2L);
        genres.add(3L);
        voteService.addVote(new VoteDto(1L, genres, "testing", "lappo.daniil2015@yandex.ru"));
    }
}
