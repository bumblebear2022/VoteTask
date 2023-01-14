package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dto.GenreDTO;
import by.itacademy.jd2.votetask.service.api.IGenreService;
import by.itacademy.jd2.votetask.service.api.IVoteService;
import by.itacademy.jd2.votetask.provider.ServiceProvider;

import java.util.List;

public class GenreService implements IGenreService {
    private final IGenresDao<GenreDTO> genresDao;

    private final IVoteService voteService = ServiceProvider.getInstance().getVoteService();

    public GenreService(IGenresDao<GenreDTO> genresDao) {
        this.genresDao = genresDao;
    }

    @Override
    public List<GenreDTO> getContent() {
        return genresDao.readAll();
    }

    @Override
    public boolean exist(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Genre title can't be empty");
        }
        return genresDao.exist(id);
    }

    @Override
    public void create(GenreDTO genreDTO) {
        genresDao.create(genreDTO);
    }

    @Override
    public void update(GenreDTO genreDTO) {
       genresDao.update(genreDTO);
    }

    @Override
    public boolean delete(Long id) {
        boolean alreadyVoted = voteService.checkVotesForGenre(id);
        if (alreadyVoted) {
            return false;
        }
        return genresDao.delete(id);
    }

    public List<GenreDTO> getGenres() {
        return genresDao.readAll();
    }
}