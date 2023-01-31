package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.domain.SavedVote;

public interface IMailService {

    void sendMail(SavedVote savedVote);
}
