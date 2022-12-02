package by.itacademy.jd2.votetask.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BestPerformer", urlPatterns = "/performers")
public class BestPerformer extends HttpServlet {

    String SHOW_PERFORMERS =
            " <p><b>Choose best performer?</b></p>" +
                    " <p>Performer 1<Br>" +
                    " <p>Performer 2<Br>" +
                    " <p>Performer 3<Br>" +
                    " <p>Performer 4<Br>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(SHOW_PERFORMERS);
    }
}
