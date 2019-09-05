package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import util.NumberValidator;

import java.lang.Class;
import java.util.ArrayList;

public class AdministratorView {

    private static Stage stage;
    private static Scene scene;
    private static BorderPane borderPane;

    private static TabPane tabPane;
    private static Tab addAssignmentTab, gradeAssignmentTab, enrollStudentTab,
            viewSchoolsTab, viewClassesTab, viewTeachersTab, viewStudentsTab, viewAssignmentsTab;

    private static VBox vBox;
    private static Button addSchoolButton, addClassButton,
            addTeacherButton, addStudentButton;

    public static void display(){
        addSchoolButton = new Button();
        addClassButton = new Button();
        addTeacherButton = new Button();
        addStudentButton = new Button();

        vBox = new VBox();

        addAssignmentTab = new Tab();
        gradeAssignmentTab = new Tab();
        enrollStudentTab = new Tab();
        viewSchoolsTab = new Tab();
        viewClassesTab = new Tab();
        viewTeachersTab = new Tab();
        viewStudentsTab = new Tab();
        viewAssignmentsTab = new Tab();

        tabPane = new TabPane();

        borderPane = new BorderPane();

        scene = new Scene(borderPane);
        stage = new Stage();

        setupButtonVbox();
        setupTabPane();
        setupBorderPane();
        setupStage();

        stage.show();
    }

    private static void setupStage() {
        stage.setScene(scene);
        stage.setMinWidth(300);
        stage.setWidth(1000);

        stage.setMinHeight(200);
        stage.setHeight(800);

        stage.setMaximized(true);
    }

    private static void setupButtonVbox() {
        vBox.setPrefWidth(150);
        vBox.getChildren().addAll(addSchoolButton, addClassButton, addTeacherButton,
                addStudentButton);

        addSchoolButton.setText("Add School");
        addSchoolButton.prefWidthProperty().bind(vBox.widthProperty());
        addSchoolButton.prefHeightProperty().bind(vBox.heightProperty());
        addSchoolButton.setOnAction( event -> AddSchool.display());

        addClassButton.setText("Add Class");
        addClassButton.prefWidthProperty().bind(vBox.widthProperty());
        addClassButton.prefHeightProperty().bind(vBox.heightProperty());
        addClassButton.setOnAction(event -> AddClass.display());

        addTeacherButton.setText("Add Teacher");
        addTeacherButton.prefWidthProperty().bind(vBox.widthProperty());
        addTeacherButton.prefHeightProperty().bind(vBox.heightProperty());
        addTeacherButton.setOnAction(event -> AddTeacher.display());

        addStudentButton.setText("Add Student");
        addStudentButton.prefWidthProperty().bind(vBox.widthProperty());
        addStudentButton.prefHeightProperty().bind(vBox.heightProperty());
        addStudentButton.setOnAction(event -> AddStudent.display());
    }

    private static void setupTabPane() {
        tabPane.getTabs().addAll(addAssignmentTab, gradeAssignmentTab, enrollStudentTab,
                viewSchoolsTab, viewClassesTab, viewTeachersTab, viewStudentsTab,
                viewAssignmentsTab);

        setupAddAssignmentTab();
        setupGradeAssignmentTab();
        setupEnrollStudentTab();
        setupViewSchoolsTab();
        setupViewClassesTab();
        setupViewTeachersTab();
        setupViewStudentsTab();
        setupViewAssignmentsTab();
    }

    private static void setupEnrollStudentTab() {
        enrollStudentTab.setText("Enroll Student");
        enrollStudentTab.setClosable(false);

        VBox enrollStudentVbox = new VBox();
        enrollStudentVbox.setSpacing(10);
        enrollStudentVbox.setPadding(new Insets(20, 10, 20, 10));

        enrollStudentTab.setContent(enrollStudentVbox);

        TextField studentIDField = new TextField();
        studentIDField.setPromptText("Student ID");

        TextField teacherIDField = new TextField();
        teacherIDField.setPromptText("Teacher ID");

        TextField classIDField = new TextField();
        classIDField.setPromptText("Class ID");


        Button enrollStudentButton = new Button("Enroll Student");
        enrollStudentButton.setOnAction(event -> {
            if (NumberValidator.isInteger(studentIDField.getText()) &&
                NumberValidator.isInteger(teacherIDField.getText()) &&
                NumberValidator.isInteger(classIDField.getText())){

                int studentID = Integer.parseInt(studentIDField.getText());
                int teacherID = Integer.parseInt(teacherIDField.getText());
                int classID = Integer.parseInt(classIDField.getText());

                DatabaseDriver.enrollStudentToClass(studentID, teacherID, classID);
            } else {
                AlertBox.display("Error Adding Assignment", "Class ID must be a" +
                        " positive integer");
            }
        });

        enrollStudentVbox.getChildren().addAll(studentIDField, teacherIDField, classIDField,
                enrollStudentButton);
    }

    private static void setupViewSchoolsTab() {
        viewSchoolsTab.setText("View Schools");
        viewSchoolsTab.setClosable(false);

        VBox viewSchoolsVBox = new VBox();
        viewSchoolsVBox.setSpacing(10);
        viewSchoolsVBox.setPadding(new Insets(20, 10, 20, 10));
        viewSchoolsTab.setContent(viewSchoolsVBox);


        TableView table = new TableView();
        ObservableList tableData = FXCollections.observableArrayList();
        tableData.addAll(DatabaseDriver.getAllSchools());
        table.setItems(tableData);

        TableColumn idCol = new TableColumn("School ID");
        idCol.setCellValueFactory(new PropertyValueFactory<School, Integer>("id"));

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<School, Integer>("name"));

        TableColumn numOfTeachersCol = new TableColumn("Number of Teachers");
        numOfTeachersCol.setCellValueFactory(new PropertyValueFactory<School, Integer>("numberOfTeachers"));

        TableColumn numOfStudentsCol = new TableColumn("Number of Students");
        numOfStudentsCol.setCellValueFactory(new PropertyValueFactory<School, Integer>("numberOfStudents"));

        table.getColumns().addAll(idCol, nameCol, numOfTeachersCol, numOfStudentsCol);


        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> {
            tableData.removeAll(tableData);

            tableData.addAll(DatabaseDriver.getAllSchools());
        });

        viewSchoolsVBox.getChildren().addAll(table);
    }

    private static void setupViewTeachersTab() {
        viewTeachersTab.setText("View Teachers");
        viewTeachersTab.setClosable(false);

        VBox viewTeachersVBox = new VBox();
        viewTeachersVBox.setSpacing(10);
        viewTeachersVBox.setPadding(new Insets(20, 10, 20, 10));
        viewTeachersTab.setContent(viewTeachersVBox);


        TableView table = new TableView();
        ObservableList tableData = FXCollections.observableArrayList();
        tableData.addAll(DatabaseDriver.getAllTeachers());
        table.setItems(tableData);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("id"));

        TableColumn schoolIDCol = new TableColumn("School ID");
        schoolIDCol.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("schoolID"));

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("email"));

        table.getColumns().addAll(idCol, schoolIDCol, nameCol, emailCol);

        HBox buttonHBox = new HBox();
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> {
            tableData.removeAll(tableData);
            tableData.addAll(DatabaseDriver.getAllTeachers());
        });

        buttonHBox.getChildren().addAll(refreshButton);
        viewTeachersVBox.getChildren().addAll(buttonHBox, table);
    }

    private static void setupViewClassesTab() {
        viewClassesTab.setText("View Classes");
        viewClassesTab.setClosable(false);

        VBox viewClassesVBox = new VBox();
        viewClassesVBox.setSpacing(10);
        viewClassesVBox.setPadding(new Insets(20, 10, 20, 10));
        viewClassesTab.setContent(viewClassesVBox);

        TextField teacherIDField = new TextField();
        teacherIDField.setPromptText("Teacher ID");

        TableView table = new TableView();
        ObservableList tableData = FXCollections.observableArrayList();
        tableData.addAll(DatabaseDriver.getAllClasses());
        table.setItems(tableData);

        TableColumn idCol = new TableColumn("Class ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Class, Integer>("id"));

        TableColumn teacherIDCol = new TableColumn("Teacher ID");
        teacherIDCol.setCellValueFactory(new PropertyValueFactory<Class, Integer>("teacherID"));


        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Class, String>("name"));

        table.getColumns().addAll(idCol, teacherIDCol, nameCol);

        HBox buttonHBox = new HBox();
        Button viewClassesButton = new Button("View Classes");
        viewClassesButton.setOnAction(event -> {
            {
                tableData.removeAll(tableData);
                tableData.addAll(DatabaseDriver.getTeachersClasses(Integer.parseInt(teacherIDField.getText())));
            }
        });

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> {
            tableData.removeAll(tableData);

            if (teacherIDField.getText().isEmpty()){
                tableData.addAll(DatabaseDriver.getAllClasses());
            } else {
                tableData.addAll(DatabaseDriver.getTeachersClasses(Integer.parseInt(teacherIDField.getText())));
            }
        });

        buttonHBox.getChildren().addAll(viewClassesButton, refreshButton);
        viewClassesVBox.getChildren().addAll(teacherIDField, buttonHBox, table);
    }

    private static void setupViewStudentsTab() {
        viewStudentsTab.setText("View Students");
        viewStudentsTab.setClosable(false);

        VBox viewStudentsVBox = new VBox();
        viewStudentsVBox.setSpacing(10);
        viewStudentsVBox.setPadding(new Insets(20, 10, 20, 10));
        viewStudentsTab.setContent(viewStudentsVBox);

        TextField teacherIDField = new TextField();
        teacherIDField.setPromptText("Teacher ID");

        Button viewStudentsButton = new Button("View Students");
        TableView table = new TableView();
        ObservableList tableData = FXCollections.observableArrayList();
        tableData.addAll(DatabaseDriver.getAllStudents());
        table.setItems(tableData);

        TableColumn idCol = new TableColumn("Student ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));

        TableColumn passwordCol = new TableColumn("Password");
        passwordCol.setCellValueFactory(new PropertyValueFactory<Student, String>("password"));

        table.getColumns().addAll(idCol, nameCol, passwordCol);

        viewStudentsButton.setOnAction(event -> {
            {
                tableData.removeAll(tableData);
                tableData.addAll(DatabaseDriver.getTeachersStudents(Integer.parseInt(teacherIDField.getText())));
            }
        });

        viewStudentsVBox.getChildren().addAll(teacherIDField, viewStudentsButton, table);
    }

    private static void setupViewAssignmentsTab() {
        viewAssignmentsTab.setText("View Assignments");
        viewAssignmentsTab.setClosable(false);

        VBox viewAssignmentVBox = new VBox();
        viewAssignmentVBox.setSpacing(10);
        viewAssignmentVBox.setPadding(new Insets(20, 10, 20, 10));
        viewAssignmentsTab.setContent(viewAssignmentVBox);

        TextField classIDField = new TextField();
        classIDField.setPromptText("Class ID");

        TableView table = new TableView();
        ObservableList tableData = FXCollections.observableArrayList();
        tableData.addAll(DatabaseDriver.getAllAssignments());
        table.setItems(tableData);


        TableColumn IDCol = new TableColumn("ID");
        IDCol.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("id"));

        TableColumn classIDCol = new TableColumn("Class ID");
        classIDCol.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("classID"));

        TableColumn studentIDCol = new TableColumn("Student ID");
        studentIDCol.setCellValueFactory(new PropertyValueFactory<Assignment, Integer>("studentID"));

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));

        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("description"));

        TableColumn gradeCol = new TableColumn("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<Assignment, Double>("grade"));

        table.getColumns().addAll(IDCol, classIDCol, studentIDCol, nameCol, descriptionCol, gradeCol);


        HBox buttonHBox = new HBox();
        Button viewAssignmentsButton = new Button("View Assignments");
        viewAssignmentsButton.setOnAction(event -> {
            {
                if (NumberValidator.isInteger(classIDField.getText())){
                    int classID = Integer.parseInt(classIDField.getText());

                    tableData.removeAll(tableData);
                    tableData.addAll(DatabaseDriver.getClassAssignments(classID));
                } else {
                    AlertBox.display("Error Viewing Assignments", "Class ID must be a" +
                            " positive integer");
                }
            }
        });

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> {
            tableData.removeAll(tableData);

           // if (NumberValidator.isInteger(classIDField.getText()) &&
           // )
            //tableData.addAll(DatabaseDriver.)
        });

        viewAssignmentVBox.getChildren().addAll(classIDField, viewAssignmentsButton, table);
    }

    private static void setupAddAssignmentTab() {
        addAssignmentTab.setText("Add Assignment");
        addAssignmentTab.setClosable(false);

        VBox addAssignmentVBox = new VBox();
        addAssignmentVBox.setSpacing(10);
        addAssignmentVBox.setPadding(new Insets(20, 10, 20, 10));
        addAssignmentTab.setContent(addAssignmentVBox);

        TextField classIDField = new TextField();
        classIDField.setPromptText("Class ID");

        TextField assignmentNameField = new TextField();
        assignmentNameField.setPromptText("Assignment Name");

        TextField assignmentDescriptionField = new TextField();
        assignmentDescriptionField.setPromptText("Assignment Description (optional)");

        Button addAssignmentButton = new Button("Add Assignment");

        addAssignmentButton.setOnAction(event -> {
            {
                if (NumberValidator.isInteger(classIDField.getText())){
                    int classID = Integer.parseInt(classIDField.getText());
                    DatabaseDriver.addAssignment(assignmentNameField.getText(),
                            assignmentDescriptionField.getText(), classID);
                } else {
                    AlertBox.display("Error Adding Assignment", "Class ID must be a" +
                            " positive integer");
                }
            }
        });

        addAssignmentVBox.getChildren().addAll(classIDField,
                assignmentNameField, assignmentDescriptionField, addAssignmentButton);
    }

    private static void setupGradeAssignmentTab() {
        gradeAssignmentTab.setText("Grade Assignments");
        gradeAssignmentTab.setClosable(false);

        VBox gradeAssignmentVbox = new VBox();
        gradeAssignmentVbox.setSpacing(10);
        gradeAssignmentVbox.setPadding(new Insets(20, 10, 20, 10));

        gradeAssignmentTab.setContent(gradeAssignmentVbox);

        TextField assignmentIDField = new TextField();
        assignmentIDField.setPromptText("Assignment ID");


        TextField gradeField = new TextField();
        gradeField.setPromptText("Grade");

        Button gradeAssignmentButton = new Button("Grade Assignment");
        gradeAssignmentButton.setOnAction(event -> {
            if (NumberValidator.isInteger(assignmentIDField.getText())){
                int assignmentID = Integer.parseInt(assignmentIDField.getText());
                DatabaseDriver.gradeAssignment(assignmentID, Double.parseDouble(gradeField.getText()));
            } else {
                AlertBox.display("Error Adding Assignment", "Class ID must be a" +
                        " positive integer");
            }
        });

        gradeAssignmentVbox.getChildren().addAll(assignmentIDField,
                gradeField, gradeAssignmentButton);
    }

    private static void setupBorderPane() {
        borderPane.setCenter(tabPane);
        borderPane.setRight(vBox);
        borderPane.setPadding(new Insets(20));
    }

}
