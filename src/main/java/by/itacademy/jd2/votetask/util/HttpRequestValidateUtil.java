package by.itacademy.jd2.votetask.util;

import by.itacademy.jd2.votetask.exceptions.InvalidHttpRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequestValidateUtil {

    public static final String REQUEST_NOT_CONTAIN_WORDS = "Performer is empty";


    public static boolean validateRequest(Map<String, String[]> parameterMap){
        List<String > errors = new ArrayList<>();
        parameterMap.entrySet().
        if () {
            errors.add(REQUEST_NOT_CONTAIN_WORDS);
        }

        if(!errors.isEmpty()){
            throw new InvalidHttpRequestException(errors);
        }
            return true;
    }
}
