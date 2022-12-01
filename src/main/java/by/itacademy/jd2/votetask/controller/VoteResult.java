package by.itacademy.jd2.votetask.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(name = "VoteResult", urlPatterns = "/vote")
public class VoteResult extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        Enumeration<String> params = req.getParameterNames();
        Map<String, String[]> parameterMap = req.getParameterMap();

        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            writer.write("<p>" + paramName + ": " + req.getParameter(paramName) + "</p>");
        }
    }
}