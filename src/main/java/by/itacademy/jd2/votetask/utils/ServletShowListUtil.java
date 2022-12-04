package by.itacademy.jd2.votetask.utils;

public class ServletShowListUtil {

    private ServletShowListUtil() {
    }

    public static String showEntityList(StringBuffer entity_header, String entity, int quantity){

        for (int i = 1; i <= quantity; i++){
        entity_header.append("<p>"+ entity + " " + i +"<Br>");
        }
        return entity_header.toString();
    }

}
