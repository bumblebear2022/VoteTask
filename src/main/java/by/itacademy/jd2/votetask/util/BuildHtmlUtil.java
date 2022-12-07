package by.itacademy.jd2.votetask.util;

import java.util.List;

public class BuildHtmlUtil {

    private static final String BR = "<br>";
    public static String build(List<String> content, String HEADER,String FOOTER) {
        String collect = String.join(BR, content);
        return HEADER + collect + FOOTER;
    }
}
