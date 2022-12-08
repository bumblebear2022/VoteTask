package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.dto.RequestDto;
import by.itacademy.jd2.votetask.exceptions.InvalidHttpRequestException;
import by.itacademy.jd2.votetask.exceptions.InvalidVoteException;
import by.itacademy.jd2.votetask.mapper.VoteMapper;
import by.itacademy.jd2.votetask.service.VoteService;
import by.itacademy.jd2.votetask.service.factories.VoteServiceSingleton;
import by.itacademy.jd2.votetask.util.HttpRequestValidateUtil;
import by.itacademy.jd2.votetask.util.VoteValidateUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private static final String PERFORMER_LOWER_CASE = "performer";
    private static final String GENRE_LOWER_CASE = "genre";
    private static final String ABOUT_LOWER_CASE = "about";
    public static final String BR = "<br>";
    private final VoteMapper voteMapper = new VoteMapper();
    private final VoteService voteService = VoteServiceSingleton.getInstance();

    private final VoteValidateUtil voteValidateUtil = new VoteValidateUtil();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        try {
            HttpRequestValidateUtil.validateRequest(parameterMap);
            LocalDateTime localDateTime = LocalDateTime.now();
            RequestDto extractedRequestDto = extract(parameterMap, localDateTime);
            voteValidateUtil.validate(extractedRequestDto);
            Vote vote = voteMapper.mapToVote(extractedRequestDto);
            voteService.addVote(vote);
            resp.sendRedirect(req.getContextPath() + "/vote_result");
        } catch (InvalidHttpRequestException e) {
            List<String> requestExceptionList = e.getRequestExceptionList();
            String exceptionsHttp = String.join(BR, requestExceptionList);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, exceptionsHttp);
        } catch (InvalidVoteException e) {
            List<String> voteExceptionList = e.getVoteExceptionList();
            String voteExceptions = String.join(BR, voteExceptionList);
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, voteExceptions);
        }
    }


    private static RequestDto extract(Map<String, String[]> parameterMap, LocalDateTime localDateTime) {
        String[] performers = parameterMap.get(PERFORMER_LOWER_CASE);
        String performer = performers[0];
        String[] genres = parameterMap.get(GENRE_LOWER_CASE);
        List<String> genresList = Arrays.asList(genres);
        String[] abouts = parameterMap.get(ABOUT_LOWER_CASE);
        String about = abouts[0];
        return new RequestDto(performer, genresList, about, localDateTime);
    }
}
