package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.content.VotesContentHolder;
import by.itacademy.jd2.votetask.domain.About;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VoteResultServlet", urlPatterns = "/vote_result")
public class VoteResultServlet extends HttpServlet {

    VotesContentHolder votesContentHolder = VotesContentHolder.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        Map<String, Integer> performers = votesContentHolder.getPerformerVotes();

        for (Map.Entry<String, Integer> performer : performers.entrySet()) {
            writer.write("<p>"+ performer.getKey() + "  " + performer.getValue() + "<p>");
        }

                Map<String, Integer> genres = votesContentHolder.getGenreVotes();

        for (Map.Entry<String, Integer> genre : genres.entrySet()) {
            writer.write("<p>"+ genre.getKey() + "  " + genre.getValue() + "<p>");
        }

        List<About> voteInfos = votesContentHolder.getVoteInfos();

        for (About about : voteInfos) {
            writer.write("<p>"+ about.getTime() + "  " + about.getText() + "<p>");
        }
    }
}