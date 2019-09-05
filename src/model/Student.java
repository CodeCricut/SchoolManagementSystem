package model;

public class Student {
    private int id;
    private String name;
    private String password;
    private double GPA;
    private School school;

    Student(int id, String name, String password,
                   double GPA, School school){
        this.id = id;
        this.name = name;
        this.password = password;
        this.GPA = GPA;
        this.school = school;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
