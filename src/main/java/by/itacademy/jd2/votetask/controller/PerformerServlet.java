package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.dto.PerformerDTO;
import by.itacademy.jd2.votetask.service.api.IPerformerService;
import by.itacademy.jd2.votetask.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PerformerServlet", urlPatterns = "/performers")
public class PerformerServlet extends HttpServlet {

    private static final String BR = "<br>";
    private static final String HEADER = "<p><b>Choose one performer:</b></p>";
    private static final String FOOTER = "<p><b>Thanks for your vote!</b></p>";
    private final String CREATE = "create";
    private final String UPDATE = "update";
    private final String DELETE = "delete";

    private final IPerformerService performerService = ServiceProvider.getInstance().getPerformerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        List<PerformerDTO> performerDTOList = performerService.getContent();
        StringBuilder str = new StringBuilder();
        for (PerformerDTO performerDTO : performerDTOList) {
            str.append(performerDTO.getId()).append(" - ").append(performerDTO.getNickName()).append(BR);
        }
        String htmlResult = HEADER + str + FOOTER;

        writer.write(htmlResult);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        PostAction postAction = extractPostAction(parameterMap);
        switch (postAction) {
            case CREATE: {
                String performerName = parameterMap.get(CREATE)[0];
                performerService.create(new PerformerDTO(null,performerName));
                break;
            }
            case UPDATE: {
                Long performerId =Long.valueOf(parameterMap.get(UPDATE)[0]);
                String performerName = parameterMap.get("name")[0];
                performerService.update(new PerformerDTO(performerId,performerName));
                break;
            }
            case DELETE: {
                Long performerId =Long.valueOf(parameterMap.get(DELETE)[0]);
                boolean delete = performerService.delete(performerId);
                if(delete){
                    writer.write("Performer deleted successfully");
                }else {
                    writer.write("This performer has already been voted for");
                }
                break;
            }
        }
    }

    private PostAction extractPostAction(Map<String, String[]> parameterMap) {

        String[] createArray = parameterMap.get(CREATE);
        boolean create = createArray != null && !createArray[0].isBlank();
            if (create) {
                return PostAction.CREATE;
            }

        String[] updateArray = parameterMap.get(UPDATE);
        boolean update = updateArray != null && !updateArray[0].isBlank();
        if (update) {
            return PostAction.UPDATE;
        }
        String[] deleteArray = parameterMap.get(DELETE);
        boolean delete = deleteArray != null && !deleteArray[0].isBlank();
        if (delete) {
            return PostAction.DELETE;
        }
        throw new IllegalStateException();
    }
}
