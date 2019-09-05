package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DatabaseDriver;
import model.Student;
import util.NumberValidator;


public class StudentLogin {
    private static Stage stage;
    private static Scene scene;
    private static VBox vBox;
    private static Label studentLoginLabel;
    private static TextField idField, passwordField;
    private static HBox buttonHBox;
    private static Button loginButton;

    public static void display(){
        loginButton = new Button("Log In");
        buttonHBox = new HBox();
        idField = new TextField();
        passwordField = new TextField();
        studentLoginLabel = new Label("Student Login");
        vBox = new VBox();
        scene = new Scene(vBox, 400, 400);
        stage = new Stage();

        // LOGIN BUTTON
        loginButton.setOnAction(event -> {
            int id = -1;
            if (NumberValidator.isInteger(idField.getText()))
                id = Integer.parseInt(idField.getText());
            else
                AlertBox.display("Login Error", "ID must be a positive integer.");
            Student student = DatabaseDriver.logStudentIn(id, passwordField.getText());

            if (student != null){
                StudentView.setStudent(student);
                StudentView.display();
            }
        });


        // BUTTON HBOX
        buttonHBox.getChildren().add(loginButton);

        // TEXT FIELDS
        idField.setPromptText("Student ID");
        passwordField.setPromptText("Student Password");

        // VBOX
        vBox.getChildren().addAll(studentLoginLabel, idField, passwordField, loginButton);

        // SCENE

        // STAGE
        stage.setScene(scene);
        stage.show();
    }
}
