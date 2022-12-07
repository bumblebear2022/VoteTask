package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.service.StatisticsService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VoteViewResultServlet", urlPatterns = "/vote_result")
public class VoteViewResultServlet extends HttpServlet {
    private static final String HEADER_PERFORMERS_RESULT = "<p>Total score among Performers:</p>";
    private static final String HEADER_GENRES_RESULT = "<p>Total score among Genres:</p>";
    private static final String HEADER_ABOUT_RESULT = "<p>Info about voters:</p>";
    private static final String BR = "<br>";

    StatisticsService statisticsService = new StatisticsService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();


    }
}