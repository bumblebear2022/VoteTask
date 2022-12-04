package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.utils.ServletShowListUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GenreServlet", urlPatterns = "/genres")
public class GenreServlet extends HttpServlet {

    int INITIAL_GENRES_QUANTITY = Genre.getQUANTITY();
    String GENRE = "Genre";
    StringBuffer HEADER_GENRES = new StringBuffer("<p><b>Choose 3-5 genres: </b></p>");
    String SHOW_GENRES = ServletShowListUtil.showEntityList(HEADER_GENRES, GENRE, INITIAL_GENRES_QUANTITY);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(SHOW_GENRES);
    }

}