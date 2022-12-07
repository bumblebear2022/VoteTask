package by.itacademy.jd2.votetask.mapper;

import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.dto.RequestDto;

import java.time.LocalDateTime;
import java.util.List;

public class VoteMapper {

    public Vote mapToVote(RequestDto requestDto){
        String voiceForPerformer = requestDto.getVoiceForPerformer();
        List<String> voicesForGenres = requestDto.getVoicesForGenres();
        String info = requestDto.getInfo();
        LocalDateTime time = requestDto.getTime();
        return new Vote(voiceForPerformer,voicesForGenres,info,time);
    }
}
