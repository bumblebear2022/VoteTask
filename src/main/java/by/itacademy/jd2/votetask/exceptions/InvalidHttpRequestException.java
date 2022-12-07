package by.itacademy.jd2.votetask.exceptions;

import java.util.List;

public class InvalidHttpRequestException extends RuntimeException {

    private final List<String> requestExceptionList;

    public InvalidHttpRequestException(List<String> requestExceptionList) {
        this.requestExceptionList = requestExceptionList;
    }

    public List<String> getRequestExceptionList() {
        return requestExceptionList;
    }
}