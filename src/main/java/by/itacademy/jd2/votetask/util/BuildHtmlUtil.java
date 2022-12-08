package by.itacademy.jd2.votetask.util;

import java.util.List;

public class BuildHtmlUtil {
    public static final String BR = "<br>";
    public static String build(List<String> content, String header,String footer) {
        String collect = String.join(BR, content);
        return header + collect + footer;
    }
}
