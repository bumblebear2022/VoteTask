package by.itacademy.jd2.votetask.domain;

import java.util.ArrayList;

public class About {
    private static About INSTANCE;

    ArrayList<String> about = new ArrayList<>();

    public static About getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new About();
        }
        return INSTANCE;
    }

    public void addAbout(String about){
        this.about.add(about);
    }

    public ArrayList<String> getAbout() {
        return about;
    }
}
