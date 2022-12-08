package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.dto.VoteResultDto;
import by.itacademy.jd2.votetask.service.StatisticsService;
import by.itacademy.jd2.votetask.service.factories.StatisticsServiceSingleton;
import by.itacademy.jd2.votetask.view.ResultViewer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@WebServlet(name = "VoteViewResultServlet", urlPatterns = "/vote_result")
public class VoteViewResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        ResultViewer viewer = new ResultViewer();

        writer.write(viewer.getView());

    }
}