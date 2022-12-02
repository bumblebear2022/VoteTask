package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.utils.VoteInfoExtractUtil;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        VoteInfoExtractUtil.infoExtraction(parameterMap);

        if(true){
            writer.write("Success");
        }else{
            //throw Http exception
        }
    }
}