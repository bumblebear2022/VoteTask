package by.itacademy.jd2.votetask.form;

import by.itacademy.jd2.votetask.dto.RequestDto;
import by.itacademy.jd2.votetask.util.HttpRequestValidateUtil;
import by.itacademy.jd2.votetask.util.VoteValidateUtil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class VoteRequestForm {
    private static final String PERFORMER_LOWER_CASE = "performer";
    private static final String GENRE_LOWER_CASE = "genre";
    private static final String ABOUT_LOWER_CASE = "about";

    public VoteRequestForm() {
    }

    public RequestDto doRead(Map<String, String[]> parameterMap) {

        HttpRequestValidateUtil.validateRequest(parameterMap);
        LocalDateTime localDateTime = LocalDateTime.now();

        RequestDto extractedRequestDto = extract(parameterMap, localDateTime);
        VoteValidateUtil.validate(extractedRequestDto);

        return extractedRequestDto;
    }

    private static RequestDto extract(Map<String, String[]> parameterMap, LocalDateTime localDateTime) {
        String[] performers = parameterMap.get(PERFORMER_LOWER_CASE);
        String performer = performers[0];
        String[] genres = parameterMap.get(GENRE_LOWER_CASE);
        List<String> genresList = Arrays.asList(genres);
        String[] abouts = parameterMap.get(ABOUT_LOWER_CASE);
        String about = abouts[0];
        return new RequestDto(performer, genresList, about, localDateTime);
    }
}
