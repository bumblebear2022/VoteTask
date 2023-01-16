package by.itacademy.jd2.votetask.dao.memory;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VoteDao implements IVoteDao<SavedVoteDTO> {

    private final List<SavedVoteDTO> savedVoteDTOS = new ArrayList<>();

    private final Set<Long> votedGenres = new HashSet<>();

    private final Set<Long> votedPerformers = new HashSet<>();

    @Override
    public void create(SavedVoteDTO savedVoteDTO) {
        savedVoteDTOS.add(savedVoteDTO);
        votedGenres.addAll(savedVoteDTO.getVote().getVoicesForGenres());
        votedPerformers.add(savedVoteDTO.getVote().getVoiceForPerformer());
    }
    @Override
    public List<SavedVoteDTO> readAll() {
        return savedVoteDTOS;
    }

    @Override
    public boolean delete(Long id) {
       return false;
    }

    public boolean isVotedGenre(Long id) {
        return votedGenres.contains(id);
    }

    public boolean isVotedPerformer(Long id) {
        return votedPerformers.contains(id);
    }
}
