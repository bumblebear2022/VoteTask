package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.dto.SavedVoteDTO;

public interface IMailService {

    void sendMail(SavedVoteDTO savedVoteDTO);
}
