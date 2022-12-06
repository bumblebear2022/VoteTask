package by.itacademy.jd2.votetask.controller;

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
    private static final String HEADER_PERFORMERS_RESULT = "<p>Total score among Performers:</p>";
    private static final String HEADER_GENRES_RESULT = "<p>Total score among Genres:</p>";
    private static final String HEADER_ABOUT_RESULT = "<p>Info about voters:</p>";
    private static final String BR = "<br>";
    private final VotesContentHolder votesContentHolder = VotesContentHolder.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        Map<String, Integer> performers = votesContentHolder.getSortedPerformerVotes();

        writer.write(HEADER_PERFORMERS_RESULT);
        for (Map.Entry<String, Integer> performer : performers.entrySet()) {
            writer.write("<p>" + performer.getKey() + "  " + performer.getValue() + "</p>");
        }

        Map<String, Integer> genres = votesContentHolder.getSortedGenreVotes();
        writer.write(BR + HEADER_GENRES_RESULT);
        for (Map.Entry<String, Integer> genre : genres.entrySet()) {
            writer.write("<p>" + genre.getKey() + "  " + genre.getValue() + "</p>");
        }

        List<About> voteInfos = votesContentHolder.getSortedVoteInfos();
        writer.write(BR + HEADER_ABOUT_RESULT);
        for (About about : voteInfos) {
            writer.write("<p>" + about.getTime() + "  " + about.getText() + "</p>");
        }
    }
}