package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.dao.IVoteDao;
import by.itacademy.jd2.votetask.dao.VoteDao;
import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.service.VoteService;
import by.itacademy.jd2.votetask.util.HttpRequestValidateUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private final IVoteDao<Vote> voteDao = new VoteDao();
    private final VoteService voteService = new VoteService(voteDao);
    private final String TAGGED_SUCCESS = "<p><b>SUCCESS</b></p>";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        boolean validatedRequest = HttpRequestValidateUtil.validateRequest(parameterMap);
        if(validatedRequest){
            voteService.addVote(parameterMap);
        }
        writer.write(TAGGED_SUCCESS);
    }
}