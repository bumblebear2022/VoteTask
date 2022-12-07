package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.service.api.IVoteService;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VoteService implements IVoteService {

    private final IVoteDao<Vote> voteDao;

    private final Lock lock = new ReentrantLock();

    public VoteService(IVoteDao<Vote> voteDao) {
        this.voteDao = voteDao;
    }

    public void addVote(Vote vote) {
        try {
            boolean isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);
            if (isLockAcquired) {
                voteDao.create(vote);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


