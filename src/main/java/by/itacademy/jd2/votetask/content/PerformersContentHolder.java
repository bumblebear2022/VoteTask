package by.itacademy.jd2.votetask.content;

import java.util.List;

public class PerformersContentHolder implements IPerformersDao {

   private static final List<String> PERFORMERS =List.of("p","p2");

   @Override
    public List<String> readAll(){
        return PERFORMERS;
    }


}
