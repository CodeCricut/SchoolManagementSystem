package model;

public class Class {

    private int id;
    private int teacherID;
    private String name;
    private double aveGrade;

    Class(int id, int teacherID, String name, double aveGrade){
        this.id = id;
        this.teacherID = teacherID;
        this.name = name;
        this.aveGrade = aveGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAveGrade() {
        return aveGrade;
    }

    public void setAveGrade(double aveGrade) {
        this.aveGrade = aveGrade;
    }
}
