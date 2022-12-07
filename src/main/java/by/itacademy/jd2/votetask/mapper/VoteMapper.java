package by.itacademy.jd2.votetask.mapper;

import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.dto.VoteDto;

import java.time.LocalDateTime;
import java.util.List;

public class VoteMapper {

    public Vote mapToVote(VoteDto voteDto){
        String voiceForPerformer = voteDto.getVoiceForPerformer();
        List<String> voicesForGenres = voteDto.getVoicesForGenres();
        String info = voteDto.getInfo();
        LocalDateTime time = voteDto.getTime();
        return new Vote(voiceForPerformer,voicesForGenres,info,time);
    }
}
