package by.itacademy.jd2.votetask.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BestGenre", urlPatterns = "/genres")
public class BestGenre extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String html = " <p><b>Choose best performer?</b></p>" +
                " <p>Genre 1<Br>" +
                " <p>Genre 2<Br>" +
                " <p>Genre 3<Br>" +
                " <p>Genre 4<Br>" +
                " <p>Genre 5<Br>" +
                " <p>Genre 6<Br>" +
                " <p>Genre 7<Br>" +
                " <p>Genre 8<Br>" +
                " <p>Genre 9<Br>" +
                " <p>Genre 10<Br>";

        writer.write(html);
    }
}