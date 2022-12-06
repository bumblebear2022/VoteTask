package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.service.genre.GenreService;
import by.itacademy.jd2.votetask.service.genre.IGenreService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GenreServlet", urlPatterns = "/genres")
public class GenreServlet extends HttpServlet {

    private static final String BR = "<br>";

    private final IGenreService genreService = new GenreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String header = genreService.getHeader();
        String footer = genreService.getFooter();
        List<String> content = genreService.getContent();
        String htmlResult = buildHtml(header, footer, content);
        writer.write(htmlResult);
    }

    private static String buildHtml(String header, String footer, List<String> content) {
        String collect = String.join(BR, content);
        return header + collect + footer;
    }
}