package by.itacademy.jd2.votetask;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
       String performer1Name = "Performer 1";
       String performer = "performer";
        Pattern pattern1 = Pattern.compile(performer1Name);
        boolean performer1 = pattern1.matcher(performer).matches();
        System.out.println(performer1);
    }
}
