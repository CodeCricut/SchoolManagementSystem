package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import model.*;
import model.Class;

import java.util.ArrayList;


public class StudentView {


    private static Student loggedInStudent = null;

    private static Stage stage;
    private static Scene scene;
    private static BorderPane borderPane;

    private static HBox topMenuBar;
    private static Button helpButton, supportButton, logoutButton;

    private static VBox leftMenuVBox;
    private static Button infoButton, gradesButton,
            toDoButton, contactTeachersButton;

    private static VBox infoVBox, gradesVBox,
            toDoVBox, contactTeachersVBox;

    private static String contentTitleHBoxStyle = "-fx-background-color: #7a7a7a;";
    private static String titleLabelStyle = "-fx-font: 30 arial;";
    private static Font contentLabelFont = new Font("Arial", 18);

    static void setStudent(Student student){
        loggedInStudent = student;
    }

    public static void display() {
        // COMPONENT SETUP
        infoVBox = createContentVBox();
        gradesVBox = createContentVBox();
        toDoVBox = createContentVBox();
        contactTeachersVBox = createContentVBox();

        infoButton = new Button("Information");
        gradesButton = new Button("Grades");
        toDoButton = new Button("To Do");
        contactTeachersButton = new Button("Contact Teachers");

        leftMenuVBox = new VBox();

        helpButton = new Button("Help");
        supportButton = new Button("Contact Support");
        logoutButton = new Button("Logout");

        topMenuBar = new HBox();

        borderPane = new BorderPane();

        scene = new Scene(borderPane, 800, 500);

        stage = new Stage();

        // CONTENT VBOXs
        setupInfoVBox();
        setupGradesVBox();
        setupToDoVBox();
        setupContactTeachersVBox();


        // TOP MENU BUTTONS
        helpButton.setOnAction(event -> HelpStage.display());
        logoutButton.setOnAction(event -> {
            loggedInStudent = null;
            StudentLogin.display();
        });

        // TOP MENU HBOX
        topMenuBar.setPrefHeight(20);
        topMenuBar.getChildren().addAll(helpButton, supportButton, logoutButton);
        helpButton.prefHeightProperty().bind(topMenuBar.heightProperty());
        supportButton.prefHeightProperty().bind(topMenuBar.heightProperty());
        logoutButton.prefHeightProperty().bind(topMenuBar.heightProperty());

        // LEFT MENU BUTTONS
        infoButton.setOnAction(event -> borderPane.setCenter(infoVBox));
        gradesButton.setOnAction(event -> borderPane.setCenter(gradesVBox));
        toDoButton.setOnAction(event -> borderPane.setCenter(toDoVBox));
        contactTeachersButton.setOnAction(event -> borderPane.setCenter(contactTeachersVBox));

        // LEFT MENU VBOX
        leftMenuVBox.setPrefWidth(120);
        leftMenuVBox.prefHeightProperty().bind(borderPane.heightProperty());
        leftMenuVBox.getChildren().addAll(infoButton, gradesButton,
                toDoButton, contactTeachersButton);
        infoButton.prefWidthProperty().bind(leftMenuVBox.widthProperty());
        infoButton.prefHeightProperty().bind(leftMenuVBox.heightProperty());
        gradesButton.prefWidthProperty().bind(leftMenuVBox.widthProperty());
        gradesButton.prefHeightProperty().bind(leftMenuVBox.heightProperty());
        toDoButton.prefWidthProperty().bind(leftMenuVBox.widthProperty());
        toDoButton.prefHeightProperty().bind(leftMenuVBox.heightProperty());
        contactTeachersButton.prefWidthProperty().bind(leftMenuVBox.widthProperty());
        contactTeachersButton.prefHeightProperty().bind(leftMenuVBox.heightProperty());

        // BORDERPANE
        borderPane.setTop(topMenuBar);
        borderPane.setLeft(leftMenuVBox);
        borderPane.setCenter(infoVBox);

        // SCENE

        // STAGE
        stage.setScene(scene);

        stage.show();
    }

    private static void setupInfoVBox() {
        // COMPONENT SETUP
        HBox titleLabelHBox = createTitleHBox("Information");

        HBox infoHBox = createContentHBox(infoVBox);

        /*
        FOR EASE OF COPYING
        staticIDLabel
        staticNameLabel
        staticPasswordLabel
        staticGPALabel
        staticGradeLabel
        staticSchoolLabel
         */

        VBox staticVBox = new VBox();
        Label staticIDLabel = new Label("ID");
        Label staticNameLabel = new Label("Name");
        Label staticPasswordLabel = new Label("Password");
        Label staticGPALabel = new Label("GPA");
        Label staticSchoolLabel = new Label("model.School");

        VBox userInfoVBox = new VBox();
        Label userIDLabel = new Label();
        Label userNameLabel = new Label();
        Label userPasswordLabel = new Label();
        Label userGPALabel = new Label();
        Label userSchoolLabel = new Label();

        // STATIC LABELS
        staticIDLabel.setFont(contentLabelFont);
        staticNameLabel.setFont(contentLabelFont);
        staticPasswordLabel.setFont(contentLabelFont);
        staticGPALabel.setFont(contentLabelFont);
        staticSchoolLabel.setFont(contentLabelFont);

        // STATIC VBOX
        staticVBox.getChildren().addAll(staticIDLabel, staticNameLabel,
                staticPasswordLabel, staticGPALabel, staticSchoolLabel);
        staticVBox.setPadding(new Insets(10));
        staticVBox.setSpacing(10);

        // USER LABELS
        userIDLabel.setFont(contentLabelFont);
        userNameLabel.setFont(contentLabelFont);
        userPasswordLabel.setFont(contentLabelFont);
        userGPALabel.setFont(contentLabelFont);
        userSchoolLabel.setFont(contentLabelFont);

        userIDLabel.setText(Integer.toString(loggedInStudent.getId()));
        userNameLabel.setText(loggedInStudent.getName());
        userPasswordLabel.setText(loggedInStudent.getPassword());
        userGPALabel.setText(Double.toString(loggedInStudent.getGPA()));
        userSchoolLabel.setText(loggedInStudent.getSchool().getName());

        // USER VBOX
        userInfoVBox.getChildren().addAll(userIDLabel, userNameLabel, userPasswordLabel,
                userGPALabel, userSchoolLabel);
        userInfoVBox.setPadding(new Insets(10));
        userInfoVBox.setSpacing(10);

        // INFO HBOX
        infoHBox.getChildren().addAll(staticVBox, userInfoVBox);

        // CONTENT VBOX
        infoVBox.getChildren().addAll(titleLabelHBox, infoHBox);

    }

    private static HBox createContentHBox(VBox parentVBox) {
        HBox contentHBox = new HBox();
        contentHBox.prefWidthProperty().bind(parentVBox.widthProperty());
        contentHBox.setStyle("-fx-background-color: #e7e7e7");
        return contentHBox;
    }

    private static void setupGradesVBox() {
        // COMPONENT SETUP
        HBox titleHBox = createTitleHBox("Grades");
        HBox contentHBox = new HBox();

        VBox buttonVbox = new VBox();
        ArrayList<Button> buttons = new ArrayList<>();

        TableView table = new TableView();

        ObservableList classes = FXCollections.observableArrayList();
        classes.addAll(DatabaseDriver.getStudentsClasses(loggedInStudent.getId()));

        table.setItems(classes);

        TableColumn classNameCol = new TableColumn("model.Class Name");
        classNameCol.setCellValueFactory(new PropertyValueFactory<Class, String>("name"));

        TableColumn gradeCol = new TableColumn("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<Class, Double>("aveGrade"));

        table.getColumns().addAll(classNameCol, gradeCol);

        // BUTTON VBOX
        for (Object myClass : classes){
            String name = ((Class) myClass).getName();
            Button button = new Button(name);
            button.setOnAction(event -> new ClassGradesStage((Class)myClass).display());
            buttons.add(button);
        }
        buttonVbox.getChildren().addAll(buttons);

        // CONTENT HBOX
        contentHBox.getChildren().addAll(table, buttonVbox);

        // CONTENT VBOX
        gradesVBox.getChildren().addAll(titleHBox, contentHBox);
    }

    private static void setupToDoVBox() {
        // COMPONENT SETUP
        HBox titleHBox = createTitleHBox("To Dos");

        TableView table = new TableView();
        ObservableList tableData = FXCollections.observableArrayList();
        tableData.addAll(DatabaseDriver.getStudentToDos(loggedInStudent.getId()));
        table.setItems(tableData);

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<ToDo, String>("title"));

        TableColumn contentCol = new TableColumn("Contents");
        contentCol.setCellValueFactory(new PropertyValueFactory<ToDo, String>("contents"));

        table.getColumns().addAll(titleCol, contentCol);

        // CONTENT VBOX
        toDoVBox.getChildren().addAll(titleHBox, table);
    }

    private static void setupContactTeachersVBox() {
        // COMPONENT SETUP
        HBox titleHBox = createTitleHBox("Contact Teachers");

        HBox contactHBox = new HBox();

        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.addAll(DatabaseDriver.getStudentsTeachers(loggedInStudent.getId()));

        VBox teacherVBox = new VBox();

        VBox emailVBox = new VBox();

        // CONTACT LABEL

        // TEACHER LABELS
        Label teacherName = new Label("Name");
        teacherName.setFont(new Font("Arial", 26));

        // EMAIL LABELS
        Label teacherEmail = new Label("Email");
        teacherEmail.setFont(new Font("Arial", 26));


        // TEACHER VBOX
        teacherVBox.getChildren().add(teacherName);
        for (Teacher teacher : teachers){
            Label name = new Label(teacher.getName());
            name.setFont(contentLabelFont);
            teacherVBox.getChildren().add(name);
        }

        // EAMIL VBOX
        emailVBox.getChildren().add(teacherEmail);
        for (Teacher teacher : teachers){
            Label email = new Label(teacher.getEmail());
            email.setFont(contentLabelFont);
            emailVBox.getChildren().add(email);
        }

        // CONTACT HBOX
        contactHBox.getChildren().addAll(teacherVBox, emailVBox);

        contactHBox.prefWidthProperty().bind(contactTeachersVBox.widthProperty());
        contactHBox.prefHeightProperty().bind(contactTeachersVBox.heightProperty());
        contactHBox.setSpacing(30);
        contactHBox.setPadding(new Insets(20, 10, 20, 10));

        // CONTENT VBOX
        contactTeachersVBox.getChildren().addAll(titleHBox, contactHBox);
    }

    private static VBox createContentVBox() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(20, 10, 20, 10));
        return vBox;
    }

    private static HBox createTitleHBox(String title) {
        HBox titleHBox = new HBox();
        Label titleLabel = new Label(title);

        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle(titleLabelStyle);

        titleHBox.getChildren().add(titleLabel);
        titleHBox.setPrefHeight(50);
        titleHBox.setStyle(contentTitleHBoxStyle);
        titleHBox.setAlignment(Pos.CENTER);

        return titleHBox;
    }
}
