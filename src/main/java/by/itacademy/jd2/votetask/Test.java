package by.itacademy.jd2.votetask;

import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.service.VoteService;
import by.itacademy.jd2.votetask.service.factories.VoteServiceSingleton;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        VoteService voteService = VoteServiceSingleton.getInstance();
        List<Long> genres = new ArrayList<>();
        genres.add(3L);
        genres.add(5L);
        genres.add(7L);
        voteService.addVote(new VoteDto(1L, genres, "testing", "lappo.daniil2017@yandex.ru"));
    }
}
