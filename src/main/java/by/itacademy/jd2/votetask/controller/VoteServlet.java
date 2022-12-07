package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.dao.VoteDao;
import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.exceptions.InvalidHttpRequestException;
import by.itacademy.jd2.votetask.exceptions.InvalidVoteException;
import by.itacademy.jd2.votetask.mapper.VoteMapper;
import by.itacademy.jd2.votetask.service.VoteService;
import by.itacademy.jd2.votetask.util.HttpRequestValidateUtil;
import by.itacademy.jd2.votetask.util.VoteValidateUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private static final String PERFORMER_LOWER_CASE = "performer";
    private static final String GENRE_LOWER_CASE = "genre";
    private static final String ABOUT_LOWER_CASE = "about";
    private final String TAGGED_SUCCESS = "<p><b>SUCCESS</b></p>";
    private final IVoteDao<Vote> voteDao = new VoteDao();

    private final VoteMapper voteMapper = new VoteMapper();
    private final VoteService voteService = new VoteService(voteDao);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        try {
            HttpRequestValidateUtil.validateRequest(parameterMap);
            LocalDateTime localDateTime = LocalDateTime.now();
            VoteDto extractedVoteDto = extract(parameterMap, localDateTime);
            VoteValidateUtil.validate(extractedVoteDto);
            Vote vote = voteMapper.mapToVote(extractedVoteDto);
            voteService.addVote(vote);
            resp.sendRedirect(req.getContextPath() + "/vote_result");
        } catch (InvalidHttpRequestException e) {
            List<String> requestExceptionList = e.getRequestExceptionList();
            for (String exception : requestExceptionList) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, exception);
            }
        } catch (InvalidVoteException e) {
            List<String> voteExceptionList = e.getVoteExceptionList();
            for (String exception : voteExceptionList) {
                resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, exception);
            }
        }
    }

    private static VoteDto extract(Map<String, String[]> parameterMap, LocalDateTime localDateTime) {
        String[] performers = parameterMap.get(PERFORMER_LOWER_CASE);
        String performer = performers[0];
        String[] genres = parameterMap.get(GENRE_LOWER_CASE);
        List<String> genresList = Arrays.asList(genres);
        String[] abouts = parameterMap.get(ABOUT_LOWER_CASE);
        String about = abouts[0];
        return new VoteDto(performer, genresList, about, localDateTime);
    }
}
