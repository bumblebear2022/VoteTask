package by.itacademy.jd2.votetask.exceptions;

import java.util.List;

public class InvalidVoteException extends RuntimeException {

    private final List<String> voteExceptionList;

    public InvalidVoteException(List<String> voteExceptionList) {
        this.voteExceptionList = voteExceptionList;
    }

    public List<String> getVoteExceptionList() {
        return voteExceptionList;
    }
}