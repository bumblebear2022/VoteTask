package by.itacademy.jd2.votetask;

import by.itacademy.jd2.votetask.domain.SavedVote;
import by.itacademy.jd2.votetask.service.AutoMailService;
import by.itacademy.jd2.votetask.service.VoteService;
import by.itacademy.jd2.votetask.service.api.IVoteService;
import by.itacademy.jd2.votetask.util.ApplicationContextHolder;

import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        IVoteService service = ApplicationContextHolder.getContext().getBean("VoteServiceBean", VoteService.class);
        List<SavedVote> list = service.readUnsentVotes();
        System.out.println(list.size());
        AutoMailService mailService = ApplicationContextHolder.getContext().getBean("AutoMailServiceBean", AutoMailService.class);
        if (list.size() > 0) {
            mailService.sendAndCheck(list.get(0));
        } else {
            System.out.println("all sent");
        }
    }
}
