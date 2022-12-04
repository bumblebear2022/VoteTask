package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.domain.About;
import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "VoteResultView", urlPatterns = "/vote_result")
public class VoteResultView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        Map<String, Integer> performers = Performer.getInstance().performersToMap();

        for (Map.Entry<String, Integer> performer : performers.entrySet()) {
            writer.write(performer.getKey() + "  " + performer.getValue() + "\n");
        }

        Map<String, Integer> genres = Genre.getInstance().genresToMap();

        for (Map.Entry<String, Integer> genre : genres.entrySet()) {
            writer.write(genre.getKey() + "  " + genre.getValue() + "\n");
        }

        ArrayList<String> abouts = About.getInstance().getAbout();

        for (String about :abouts){
            writer.write("About :" + about + "\n");
        }
    }
}