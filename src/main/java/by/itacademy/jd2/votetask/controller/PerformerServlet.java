package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.utils.ServletShowListUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PerformerServlet", urlPatterns = "/performers")
public class PerformerServlet extends HttpServlet {

    int INITIAL_PERFORMERS_QUANTITY = Performer.getQUANTITY();
    String PERFORMER = "Performer";
    StringBuffer HEADER_PERFORMERS = new StringBuffer("<p><b>Choose one performer:</b></p>");
    String SHOW_PERFORMERS = ServletShowListUtil.showEntityList(HEADER_PERFORMERS, PERFORMER, INITIAL_PERFORMERS_QUANTITY);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(SHOW_PERFORMERS);
    }
}
