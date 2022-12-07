package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.service.api.IVoteService;
import by.itacademy.jd2.votetask.util.VoteValidateUtil;

import java.time.LocalDateTime;
import java.util.List;

public class VoteService implements IVoteService {

    private final IVoteDao<Vote> voteDao;

    public VoteService(IVoteDao<Vote> voteDao) {
        this.voteDao = voteDao;
    }
    private Vote mapToVote(VoteDto voteDto){
        String voiceForPerformer = voteDto.getVoiceForPerformer();
        List<String> voicesForGenres = voteDto.getVoicesForGenres();
        String info = voteDto.getInfo();
        LocalDateTime time = voteDto.getTime();
        return new Vote(voiceForPerformer,voicesForGenres,info,time);
    }

    public void addVote(VoteDto voteDto){
        Vote vote = mapToVote(voteDto);
        VoteValidateUtil.validate(vote);
        voteDao.create(vote);
    }

}
