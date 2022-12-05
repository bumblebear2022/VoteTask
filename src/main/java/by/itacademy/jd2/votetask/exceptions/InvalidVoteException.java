package by.itacademy.jd2.votetask.exceptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InvalidVoteException extends RuntimeException{

    public InvalidVoteException(List<String> exceptionList, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("exceptionList", exceptionList);
        try {
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
