package by.itacademy.jd2.votetask.content;

import java.util.List;

public class PerformersContentHolder implements IPerformersDao {

   private static final List<String> PERFORMERS =List.of("Performer 1","Performer 2","Performer 3","Performer 4");

   @Override
    public List<String> readAll(){
        return PERFORMERS;
    }


}
