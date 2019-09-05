package model;

import view.AlertBox;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseDriver {

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_management_system", "root", "Database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //--------------------------------------------------GET BY ID--------------------------------------------------\\

    private static School getSchoolByID(int id) {
        try {
            CallableStatement getSchool = connection.prepareCall(
                    "{CALL get_school_by_id(?)}"
            );

            getSchool.setInt(1, id);

            ResultSet schoolResult = getSchool.executeQuery();
            schoolResult.next();

            String name = schoolResult.getString("name");
            int numberOfTeachers = getNumberOfTeachersInSchool(id);
            int numberOfStudents = getNumberOfStudentsInSchool(id);

            return new School(id, name, numberOfTeachers, numberOfStudents);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error Retrieving model.School", "Error getting school by id.");
            return null;
        }
    }

    private static Student getStudentByID(int id) {
        try {
            CallableStatement getStudent = connection.prepareCall(
                    "{CALL get_student_by_id(?)}"
            );

            getStudent.setInt(1, id);

            ResultSet studentResult = getStudent.executeQuery();
            studentResult.next();

            String name = studentResult.getString("name");
            String password = studentResult.getString("password");
            double gpa = getGPA(id);
            School school = getSchoolByID(studentResult.getInt("school_id"));

            return new Student(id, name, password, gpa, school);
        } catch (Exception e) {
            e.printStackTrace();
            AlertBox.display("Error getting student", "Error getting student by id");
        }
        return null;
    }

    private static Teacher getTeacherByID(int id) {
        try {
            CallableStatement getTeacher = connection.prepareCall(
                    "{CALL get_teacher_by_id(?)}"
            );

            getTeacher.setInt(1, id);

            ResultSet teacherResult = getTeacher.executeQuery();
            teacherResult.next();

            int schoolID = teacherResult.getInt("school_id");
            String name = teacherResult.getString("name");
            String email = teacherResult.getString("email");

            return new Teacher(id, schoolID, name, email);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error getting teacher by id", "Error");
            return null;
        }
    }

    private static Class getClassByID(int id) {
        try {
            CallableStatement getClass = connection.prepareCall(
                    "{CALL get_class_by_id(?)}"
            );

            getClass.setInt(1, id);

            ResultSet classResult = getClass.executeQuery();
            classResult.next();

            int teacherID = getClassesTeacherID(id);
            String name = classResult.getString("name");

            return new Class(id, teacherID, name, -1);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error getting class by id", "Error");
            return null;
        }
    }

    private static Assignment getAssignmentByID(int id) {
        try {
            CallableStatement getAssignment = connection.prepareCall(
                    "{CALL get_assignment_by_id(?)}"
            );

            getAssignment.setInt(1, id);

            ResultSet assignmentResult = getAssignment.executeQuery();
            assignmentResult.next();

            int classID = assignmentResult.getInt("class_id");
            int studentID = assignmentResult.getInt("student_id");
            double grade = assignmentResult.getDouble("grade");
            String name = assignmentResult.getString("name");
            String description = assignmentResult.getString("description");

            return new Assignment(id, classID, studentID, grade, name, description);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error getting assignment by id", "Error");
            return null;
        }
    }

    //--------------------------------------------------GET ALL...--------------------------------------------------\\

    public static ArrayList<Class> getAllClasses(){
        try {
            CallableStatement getClasses = connection.prepareCall(
                    "{CALL get_all_classes()}"
            );

            ResultSet classResults = getClasses.executeQuery();

            ArrayList<Class> classes = new ArrayList<>();
            while (classResults.next()) {
                classes.add(getClassByID(classResults.getInt("id")));
            }
            return classes;
        } catch (SQLException e) {
            AlertBox.display("Get all classes error", "error");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Student> getAllStudents(){
        try {
            CallableStatement getStudents = connection.prepareCall(
                    "{CALL get_all_students()}"
            );

            ResultSet studentResults = getStudents.executeQuery();

            ArrayList<Student> students = new ArrayList<>();
            while (studentResults.next()) {
                students.add(getStudentByID(studentResults.getInt("id")));
            }
            return students;
        } catch (SQLException e) {
            AlertBox.display("Get all students error", "error");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<School> getAllSchools(){
        try {
            CallableStatement getSchools = connection.prepareCall(
                    "{CALL get_all_schools()}"
            );

            ResultSet schoolResults = getSchools.executeQuery();

            ArrayList<School> schools = new ArrayList<>();
            while (schoolResults.next()) {
                School school = getSchoolByID(schoolResults.getInt("id"));
                schools.add(school);
            }
            return schools;
        } catch (SQLException e) {
            AlertBox.display("Get all schools error", "error");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Assignment> getAllAssignments(){
        try {
            CallableStatement getAssignments = connection.prepareCall(
                    "{CALL get_all_assignments()}"
            );

            ResultSet assignmentResults = getAssignments.executeQuery();

            ArrayList<Assignment> assignments = new ArrayList<>();
            while (assignmentResults.next()) {
                assignments.add(getAssignmentByID(assignmentResults.getInt("id")));
            }
            return assignments;
        } catch (SQLException e) {
            AlertBox.display("Get all assignments error", "error");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Teacher> getAllTeachers(){
        try {
            CallableStatement getTeachers = connection.prepareCall(
                    "{CALL get_all_teachers()}"
            );

            ResultSet teacherResults = getTeachers.executeQuery();

            ArrayList<Teacher> teachers = new ArrayList<>();
            while (teacherResults.next()) {
                teachers.add(getTeacherByID(teacherResults.getInt("id")));
            }
            return teachers;
        } catch (SQLException e) {
            AlertBox.display("Get all teachers error", "error");
            e.printStackTrace();
            return null;
        }
    }

    //--------------------------------------------------GETTERS--------------------------------------------------\\

    public static ArrayList<Class> getStudentsClasses(int studentID) {
        try {
            CallableStatement getClassIds = connection.prepareCall(
                    "{CALL get_class_ids_by_student_id(?)}"
            );
            getClassIds.setInt(1, studentID);

            ResultSet classIDs = getClassIds.executeQuery();

            ArrayList<Class> classes = new ArrayList<>();
            while (classIDs.next()) {
                Class myClass = getClassByID(classIDs.getInt("class_id"));
                classes.add(myClass);
            }
            return classes;
        } catch (SQLException e) {
            AlertBox.display("Get Classes Error", "Error retrieving class by student id");
            e.printStackTrace();
            return null;
        }
    }

    private static double getGPA(int studentID) {
        ArrayList<Class> classes = getStudentsClasses(studentID);

        double totalPercentage = 0;
        for (Class myClass : classes) {
            totalPercentage += getClassAverage(myClass.getId(), studentID);
        }
        return totalPercentage / classes.size() / 20 - 1;
    }

    public static ArrayList<ToDo> getStudentToDos(int studentID) {
        try {
            CallableStatement getToDos = connection.prepareCall(
                    "{CALL get_student_to_dos(?)}"
            );
            getToDos.setInt(1, studentID);

            ResultSet toDoResults = getToDos.executeQuery();

            ArrayList<ToDo> toDos = new ArrayList<>();
            while (toDoResults.next()) {
                String title = toDoResults.getString("title");
                String contents = toDoResults.getString("contents");

                toDos.add(new ToDo(title, contents));
            }
            return toDos;
        } catch (SQLException e) {
            AlertBox.display("Get Classes Error", "Error retrieving class by student id");
            e.printStackTrace();
            return null;
        }
    }

    private static int getClassesTeacherID(int classID){
        try {
            CallableStatement getTeacherID = connection.prepareCall(
                    "{CALL get_classes_teacher_id(?)}"
            );

            getTeacherID.setInt(1, classID);

            ResultSet idResult = getTeacherID.executeQuery();
            idResult.next();
            int teacherID = idResult.getInt("teacher_id");

            return teacherID;

        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error retrieving class's teacher", "Error");
            return -1;
        }
    }

    public static ArrayList<Assignment> getTeachersAssignments(int teacherID) {
        try {
            CallableStatement getAssignments = connection.prepareCall(
                    "{CALL get_assignments_by_class_id(?)}"
            );

            getAssignments.setInt(1, teacherID);

            ResultSet assignmentResults = getAssignments.executeQuery();

            ArrayList<Assignment> assignments = new ArrayList<>();
            while (assignmentResults.next()) {
                assignments.add(getAssignmentByID(assignmentResults.getInt("id")));
            }
            return assignments;

        } catch (Exception e) {
            AlertBox.display("Get model.Class Assignments Error", "Error retrieving class assignment");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Assignment> getClassAssignments(int classId) {
        try {
            CallableStatement getAssignments = connection.prepareCall(
                    "{CALL get_assignments_by_class_id(?)}"
            );

            getAssignments.setInt(1, classId);

            ResultSet assignmentResults = getAssignments.executeQuery();

            ArrayList<Assignment> assignments = new ArrayList<>();
            while (assignmentResults.next()) {
                assignments.add(getAssignmentByID(assignmentResults.getInt("id")));
            }
            return assignments;

        } catch (Exception e) {
            AlertBox.display("Get model.Class Assignments Error", "Error retrieving class assignment");
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<Student> getStudentsInClasses(int classID) {
        try {
            CallableStatement getStudentIDs = connection.prepareCall(
                    "{CALL get_student_ids_by_class_id(?)}"
            );

            getStudentIDs.setInt(1, classID);

            ResultSet studentIDResults = getStudentIDs.executeQuery();

            ArrayList<Student> students = new ArrayList<>();
            while (studentIDResults.next()) {
                Student student = getStudentByID(studentIDResults.getInt("student_id"));
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error getting students in class", "Error.");
            return null;
        }
    }

    public static ArrayList<Teacher> getStudentsTeachers(int id) {
        try {
            CallableStatement getTeachersId = connection.prepareCall(
                    "{CALL get_teachers_id_by_student_id(?)}"
            );
            getTeachersId.setInt(1, id);

            ResultSet teacherIDs = getTeachersId.executeQuery();

            ArrayList<Teacher> teachers = new ArrayList<>();
            while (teacherIDs.next()) {
                teachers.add(getTeacherByID(teacherIDs.getInt("teacher_id")));
            }
            return teachers;
        } catch (SQLException e) {
            AlertBox.display("Get students Error", "An error occurred when trying to add a new book.");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Assignment> getStudentsAssignments(int id) {
        try {
            CallableStatement getAssignmentIds = connection.prepareCall(
                    "{CALL get_assignments_by_student_id(?)}"
            );
            getAssignmentIds.setInt(1, id);

            ResultSet assignmentResults = getAssignmentIds.executeQuery();

            ArrayList<Assignment> assignments = new ArrayList<>();
            while (assignmentResults.next()) {
                assignments.add(getAssignmentByID(assignmentResults.getInt("id")));
            }
            return assignments;
        } catch (SQLException e) {
            AlertBox.display("Add Book Error", "An error occurred when trying to add a new book.");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Student> getTeachersStudents(int id) {
        try {
            // get student ids
            CallableStatement getStudentIDs = connection.prepareCall(
                    "{CALL get_student_ids_by_teacher_id(?)}"
            );
            getStudentIDs.setInt(1, id);

            ResultSet studentIDResults = getStudentIDs.executeQuery();

            ArrayList<Student> students = new ArrayList<>();
            while (studentIDResults.next()) {
                students.add(getStudentByID(studentIDResults.getInt("student_id")));
            }
            return students;
        } catch (SQLException e) {
            AlertBox.display("Get teachers students error", "error");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Class> getTeachersClasses(int id) {
        try {
            // get class ids
            CallableStatement getClassIDs = connection.prepareCall(
                    "{CALL get_class_ids_by_teacher_id(?)}"
            );
            getClassIDs.setInt(1, id);

            ResultSet classIDResults = getClassIDs.executeQuery();

            ArrayList<Class> classes = new ArrayList<>();
            while (classIDResults.next()) {
                classes.add(getClassByID(classIDResults.getInt("class_id")));
            }
            return classes;
        } catch (SQLException e) {
            AlertBox.display("Add Teacher's Classes error", "error");
            e.printStackTrace();
            return null;
        }
    }

    //--------------------------------------------------ADDERS--------------------------------------------------\\

    public static void addSchool(String name) {
        try {
            CallableStatement addSchool = connection.prepareCall(
                    "{CALL add_school(?)}"
            );

            addSchool.setString(1, name);

            addSchool.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error adding school", "Error");
        }
    }

    public static void addStudentToDo(int studentID, String title, String contents) {
        try {
            CallableStatement addToDo = connection.prepareCall(
                    "{CALL add_student_to_do(?,?,?)}"
            );
            addToDo.setInt(1, studentID);
            addToDo.setString(2, title);
            addToDo.setString(3, contents);

            addToDo.execute();
        } catch (SQLException e) {
            AlertBox.display("Add model.ToDo Error", "Error adding a to do.");
            e.printStackTrace();
        }
    }

    public static void addTeacher(String name, String email, int schoolID) {
        try {
            CallableStatement addteacher = connection.prepareCall(
                    "{CALL add_teacher(?,?,?)}"
            );
            addteacher.setString(1, name);
            addteacher.setString(2, email);
            addteacher.setInt(3, schoolID);

            addteacher.execute();
        } catch (SQLException e) {
            AlertBox.display("Add teacher Error", "Error.");
            e.printStackTrace();
        }
    }

    public static void addClass(String name, int teacherID) {
        try {
            CallableStatement addClass = connection.prepareCall(
                    "{CALL add_class(?,?)}"
            );
            addClass.setInt(1, teacherID);
            addClass.setString(2, name);

            addClass.execute();
        } catch (SQLException e) {
            AlertBox.display("Add class Error", "Error.");
            e.printStackTrace();
        }
    }

    public static void addStudent(String name, String password, int schoolID) {
        try {
            CallableStatement addStudent = connection.prepareCall(
                    "{CALL add_student(?,?,?)}"
            );
            addStudent.setString(1, name);
            addStudent.setString(2, password);
            addStudent.setInt(3, schoolID);

            addStudent.execute();
        } catch (SQLException e) {
            AlertBox.display("Add student Error", "Error.");
            e.printStackTrace();
        }
    }

    public static void addAssignment(String name, String description, int classID) {
        // get all students in the class
        ArrayList<Student> studentsInClass = getStudentsInClasses(classID);

        // add assignment to each student
        for (Student student : studentsInClass) {
            try {
                PreparedStatement addAssignment = connection.prepareCall(
                        "{CALL add_assignment(?,?,?,?)}"
                );

                addAssignment.setInt(1, classID);
                addAssignment.setInt(2, student.getId());
                addAssignment.setString(3, name);
                addAssignment.setString(4, description);

                addAssignment.execute();
            } catch (Exception e) {
                e.printStackTrace();
                AlertBox.display("Error Adding Assignment", "Could not add " +
                        "assignment to students in class");
            }
        }
    }

    //---------------------------------------------------HELPERS---------------------------------------------------\\

    private static int getNumberOfTeachersInSchool(int schoolID){
        try {
            CallableStatement getTeacherIDs = connection.prepareCall(
                    "{CALL get_teacher_ids_by_school_id(?)}"
            );
            getTeacherIDs.setInt(1, schoolID);

            ResultSet teacherIDResults = getTeacherIDs.executeQuery();

            int numOfTeachers = 0;
            while (teacherIDResults.next()){
                numOfTeachers ++;
            }
            return numOfTeachers;

        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error getting schools teachers", "ERROR");
            return -1;
        }
    }

    private static int getNumberOfStudentsInSchool(int schoolID){
        try {
            CallableStatement getStudentIDs = connection.prepareCall(
                    "{CALL get_student_ids_by_school_id(?)}"
            );
            getStudentIDs.setInt(1, schoolID);

            ResultSet studentIDResults = getStudentIDs.executeQuery();

            int numOfStudents = 0;
            while (studentIDResults.next()){
                numOfStudents ++;
            }
            return numOfStudents;

        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error getting schools students", "ERROR");
            return -1;
        }
    }

    private static double getClassAverage(int classId, int studentId) {
        try {
            CallableStatement getClassAverage = connection.prepareCall(
                    "{CALL get_overall_class_grade(?,?)}"
            );

            getClassAverage.setInt(1, classId);
            getClassAverage.setInt(2, studentId);

            ResultSet gradeResult = getClassAverage.executeQuery();
            gradeResult.next();
            return gradeResult.getDouble("average_grade");
        } catch (Exception e) {
            AlertBox.display("Get model.Class Average Error", "Error retrieving class average");
            e.printStackTrace();
            return -1;
        }
    }

    //---------------------------------------------------ETCETERA---------------------------------------------------\\

    public static Student logStudentIn(int studentID, String password) {
        try {
            CallableStatement getStudent = connection.prepareCall(
                    "{CALL get_student_by_id(?)}"
            );
            getStudent.setInt(1, studentID);

            ResultSet studentResult = getStudent.executeQuery();
            studentResult.next();

            if (!studentResult.getString("password").equals(password)) {
                AlertBox.display("Login Error", "Incorrect username or password.");
                return null;
            }

            String name = studentResult.getString("name");
            double gpa = getGPA(studentID);
            School school = getSchoolByID(studentResult.getInt("school_id"));

            return new Student(studentID, name,
                    password, gpa, school);
        } catch (SQLException e) {
            AlertBox.display("Login Error", "Error logging student in.");
            e.printStackTrace();
            return null;
        }
    }

    public static void enrollStudentToClass(int studentID, int teacherID, int classID) {
        try {
            CallableStatement enrollStudent = connection.prepareCall(
                    "{CALL add_student_to_class(?,?,?)}"
            );

            enrollStudent.setInt(1, studentID);
            enrollStudent.setInt(2, teacherID);
            enrollStudent.setInt(3, classID);

            enrollStudent.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error enrolling student", "Error");
        }
    }

    public static void gradeAssignment(int assignmentID, double grade) {
        try {

            // grade the assignment
            CallableStatement gradeAssignment = connection.prepareCall(
                    "{CALL grade_assignment(?,?)}"
            );

            gradeAssignment.setInt(1, assignmentID);
            gradeAssignment.setDouble(2, grade);

            gradeAssignment.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertBox.display("Error grading assignment", "Can't grade assignment.");
        }
    }

}
