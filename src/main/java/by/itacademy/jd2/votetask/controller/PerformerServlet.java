package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.dao.IPerformersDao;
import by.itacademy.jd2.votetask.dao.PerformersDao;
import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.service.IPerformerService;
import by.itacademy.jd2.votetask.service.PerformerService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PerformerServlet", urlPatterns = "/performers")
public class PerformerServlet extends HttpServlet {

    private static final String HEADER = "<p><b>Choose one performer:</b></p>";

    private static final String FOOTER = "<p><b>Thanks for your vote!</b></p>";
    private static final String BR = "<br>";
    private final IPerformersDao<Performer> performersDao = new PerformersDao();
    private final IPerformerService performerService = new PerformerService(performersDao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        List<String> content = performerService.getContent();
        String htmlResult = buildHtml(content);
        writer.write(htmlResult);
    }

    private static String buildHtml(List<String> content) {
        String collect = String.join(BR, content);
        return HEADER + collect + FOOTER;
    }
}
