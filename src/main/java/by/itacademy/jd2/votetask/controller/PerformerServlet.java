package by.itacademy.jd2.votetask.controller;

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


    public static final String BR = "<br/>";
    private final IPerformerService performerService = new PerformerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String header = performerService.getHeader();
        List<String> content = performerService.getContent();
        String htmlResult = buildHtml(header, content);
        writer.write(htmlResult);
    }

    private static String buildHtml(String header, List<String> content) {
        String collect = String.join(BR, content);
        return header + collect;
    }



}
