package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.service.api.IVoteService;
import by.itacademy.jd2.votetask.util.VoteValidateUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class VoteService implements IVoteService {

    private final IVoteDao<Vote> voteDao;

    public VoteService(IVoteDao<Vote> voteDao) {
        this.voteDao = voteDao;
    }

    private static final String PERFORMER_LOWER_CASE = "performer";
    private static final String GENRE_LOWER_CASE = "genre";
    private static final String ABOUT_LOWER_CASE = "about";

    private Vote extract(Map<String, String[]> voteMap) {
        String[] performers = voteMap.get(PERFORMER_LOWER_CASE);
        String performer = performers[0];
        String[] genres = voteMap.get(GENRE_LOWER_CASE);
        List<String> genresList = Arrays.asList(genres);
        String[] abouts = voteMap.get(ABOUT_LOWER_CASE);
        String about = abouts[0];
        return new Vote(performer, genresList, about);
    }

    public void addVote(Map<String, String[]> voteMap){
        Vote extractedVote = extract(voteMap);
        VoteValidateUtil.validate(extractedVote);
        voteDao.create(extractedVote);
    }

}
