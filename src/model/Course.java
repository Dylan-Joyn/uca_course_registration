package src.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    public String code;
    public String title;
    public int capacity;
    public List<String> roster = new ArrayList<>();
    public List<String> waitlist = new ArrayList<>();

    public Course(String code, String title, int capacity) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
    }
}

