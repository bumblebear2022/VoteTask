package by.itacademy.jd2.votetask.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortMapUtil {

    public static Map<String, Integer> sortMap(Map<String, Integer> srcMap) {

        Map<String, Integer> sortedMap = Collections.synchronizedMap(new LinkedHashMap<>());

        ArrayList<Integer> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : srcMap.entrySet()) {
            list.add(entry.getValue());
        }

        list.sort(Comparator.reverseOrder());

        for (Integer value : list) {
            for (Map.Entry<String, Integer> entry : srcMap.entrySet()) {
                if (entry.getValue().equals(value)) {
                    sortedMap.put(entry.getKey(), value);
                }
            }
        }
        return sortedMap;
    }
}
