package by.itacademy.jd2.votetask.util;

import by.itacademy.jd2.votetask.exceptions.InvalidHttpRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class HttpRequestValidateUtil {

    public static final String KEY_HAVE_NO_WORDS = "Invalid key in request";
    public static final String VALUE_HAVE_NO_WORDS = "Invalid value in request";

    private static final Pattern CHECK_PATTERN = Pattern.compile("\\w+");

    public static void validateRequest(Map<String, String[]> parameterMap) {
        List<String> errors = new ArrayList<>();
        for (Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
            String key = parameter.getKey();
            boolean keyCheck = CHECK_PATTERN.matcher(key).find();
            if (!keyCheck) {
                errors.add(KEY_HAVE_NO_WORDS + " " + key);
            }
            String[] values = parameter.getValue();
            for (String value : values) {
                boolean valueCheck = CHECK_PATTERN.matcher(value).find();
                if (!valueCheck) {
                    errors.add(VALUE_HAVE_NO_WORDS + " " + value);
                }
            }
        }
        if (!errors.isEmpty()) {
            throw new InvalidHttpRequestException(errors);
        }
    }
}
