package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.dto.RequestDto;
import by.itacademy.jd2.votetask.exceptions.InvalidHttpRequestException;
import by.itacademy.jd2.votetask.exceptions.InvalidVoteException;
import by.itacademy.jd2.votetask.form.VoteRequestForm;
import by.itacademy.jd2.votetask.mapper.VoteMapper;
import by.itacademy.jd2.votetask.service.VoteService;
import by.itacademy.jd2.votetask.service.factories.VoteServiceSingleton;
import by.itacademy.jd2.votetask.util.VoteValidateUtil;
import by.itacademy.jd2.votetask.util.BuildHtmlUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    private final VoteMapper voteMapper = new VoteMapper();
    private final VoteService voteService = VoteServiceSingleton.getInstance();

    private final VoteValidateUtil voteValidateUtil = new VoteValidateUtil();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        Map<String, String[]> parameterMap = req.getParameterMap();

        try {
            VoteRequestForm form = new VoteRequestForm(voteValidateUtil);

            RequestDto extractedRequestDto = form.doRead(parameterMap);

            Vote vote = voteMapper.mapToVote(extractedRequestDto);
            voteService.addVote(vote);

            resp.sendRedirect(req.getContextPath() + "/vote_result");
        } catch (InvalidHttpRequestException e) {
            List<String> requestExceptionList = e.getRequestExceptionList();
            String exceptionsHttp = String.join(BuildHtmlUtil.BR, requestExceptionList);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, exceptionsHttp);
        } catch (InvalidVoteException e) {
            List<String> voteExceptionList = e.getVoteExceptionList();
            String voteExceptions = String.join(BuildHtmlUtil.BR, voteExceptionList);
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, voteExceptions);
        }
    }
}
