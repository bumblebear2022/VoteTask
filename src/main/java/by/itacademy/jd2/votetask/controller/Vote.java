package by.itacademy.jd2.votetask.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Vote", urlPatterns = "/vote")
public class Vote extends HttpServlet {


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html; charset=UTF-8");
//        PrintWriter writer = resp.getWriter();
//        Map<String, String[]> parameterMap = req.getParameterMap();
//        VoteInfoExtractUtil.infoExtraction(parameterMap);
//
//        if(true){
//            writer.write("Success");
//        }else{
//            //throw Http exception
//        }
//    }
}