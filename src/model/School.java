package model;

public class School {
    private int id;
    private String name;
    private int numberOfTeachers;
    private int numberOfStudents;

    School(int id, String name, int numberOfTeachers, int numberOfStudents){
        this.id = id;
        this.name = name;
        this.numberOfTeachers = numberOfTeachers;
        this.numberOfStudents = numberOfStudents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfTeachers() {
        return numberOfTeachers;
    }

    public void setNumberOfTeachers(int numberOfTeachers) {
        this.numberOfTeachers = numberOfTeachers;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
}
