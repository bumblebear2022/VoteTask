package by.itacademy.jd2.votetask;

import by.itacademy.jd2.votetask.domain.SavedVote;
import by.itacademy.jd2.votetask.service.AutoMailService;
import by.itacademy.jd2.votetask.service.api.IAutoMailService;
import by.itacademy.jd2.votetask.service.api.IVoteService;
import by.itacademy.jd2.votetask.service.factories.AutoMailServiceSingleton;
import by.itacademy.jd2.votetask.service.factories.VoteServiceSingleton;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        IVoteService service = VoteServiceSingleton.getInstance();
        List<SavedVote> list = service.readUnsentVotes();
        System.out.println(list.size());
        AutoMailService mailService = new AutoMailService();
        mailService.sendAndCheck(list.get(0));
        mailService.sendAndCheck(list.get(1));
    }
}
