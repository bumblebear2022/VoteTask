package by.itacademy.jd2.votetask.web.controller;

import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.dto.GenreDto;
import by.itacademy.jd2.votetask.service.GenreService;
import by.itacademy.jd2.votetask.service.api.IGenreService;
import by.itacademy.jd2.votetask.util.ApplicationContextHolder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GenreServlet", urlPatterns = "/genres")
public class GenreServlet extends HttpServlet {

    private static final String BR = "<br>";
    private static final String HEADER = "<p><b>Choose 3-5 genres:</b></p>";
    private static final String FOOTER = "<p><b>Also write few words in about section...</b></p>";
    private final String CREATE = "create";
    private final String UPDATE = "update";
    private final String DELETE = "delete";
    private final IGenreService genreService = ApplicationContextHolder.getContext().getBean("GenreServiceBean", GenreService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<Genre> genreList = genreService.getContent();
        StringBuilder str = new StringBuilder();
        for(Genre genre : genreList){
            str.append(genre.getId()).append(" - ").append(genre.getTitle()).append(BR);
        }
        String htmlResult = HEADER + str + FOOTER;
        writer.write(htmlResult);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        PostAction postAction = extractPostAction(parameterMap);
        switch (postAction) {
            case CREATE: {
                String genreName = parameterMap.get(CREATE)[0];
                genreService.create(new GenreDto(null, genreName));
                break;
            }
            case UPDATE: {
                Long genreId =Long.valueOf(parameterMap.get(UPDATE)[0]);
                String genreName = parameterMap.get("name")[0];
                genreService.update(new GenreDto(genreId, genreName));
                break;
            }
            case DELETE: {
                Long genreId =Long.valueOf(parameterMap.get(DELETE)[0]);
                boolean delete = genreService.delete(genreId);
                if(delete){
                    writer.write("Genre deleted successfully");
                }else {
                    writer.write("This genre has already been voted for");
                }
                break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/genres");
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