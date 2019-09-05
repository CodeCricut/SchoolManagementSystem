package model;

public class Teacher {

    private int id;
    private School school;
    private String name;
    private String email;
    private String password;

    Teacher(int id, School school, String name, String email,
            String password){
        this.id = id;
        this.school = school;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolID() {
        return school.getId();
    }

    public void setSchoolID(int schoolID) {
        this.school.setId(schoolID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
