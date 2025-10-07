package model;

public class Student {
    public String id;
    public String name;
    public String email;

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return id + " " + name + " <" + email + ">";
    }
}

