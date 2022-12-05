package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.service.VoteExtractInfo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "VoteResultServlet", urlPatterns = "/vote_result")
public class VoteResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        Map<String, Integer> performers = VoteExtractInfo.getInstance().performersToMap();

        for (Map.Entry<String, Integer> performer : performers.entrySet()) {
            writer.write("<p>"+ performer.getKey() + "  " + performer.getValue() + "<Br>");
        }

                Map<String, Integer> genres = VoteExtractInfo.getInstance().genresToMap();

        for (Map.Entry<String, Integer> genre : genres.entrySet()) {
            writer.write("<p>"+ genre.getKey() + "  " + genre.getValue() + "<Br>");
        }

//        Map<LocalTime, String> abouts = About.getInstance().getAbout();
//
//        for (Map.Entry<LocalTime, String> about : abouts.entrySet()) {
//            writer.write("<p>"+ about.getKey() + "  " + about.getValue() + "<Br>");
//        }
    }
}