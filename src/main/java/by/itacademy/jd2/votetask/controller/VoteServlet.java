package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.service.VoteExtractInfo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        VoteExtractInfo voteExtractInfo = VoteExtractInfo.getInstance();
        boolean genreException = voteExtractInfo.infoExtraction(parameterMap);


        if(genreException){
            throw new IllegalArgumentException("Genre Exception");
        }else{
            writer.write("Success");
        }
    }
}