package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DatabaseDriver;
import model.Teacher;
import util.NumberValidator;

public class AdministratorLogin {

    private static Stage stage;
    private static Scene scene;
    private static VBox vBox;
    private static Label teacherLoginLabel;
    private static TextField idField, passwordField;
    private static HBox buttonHBox;
    private static Button loginButton, studentLoginButton;

    public static void display(){
        loginButton = new Button("Log In");
        studentLoginButton = new Button("Student Login");
        buttonHBox = new HBox();
        idField = new TextField();
        passwordField = new TextField();
        teacherLoginLabel = new Label("Teacher Login");
        vBox = new VBox();
        scene = new Scene(vBox, 400, 400);
        stage = new Stage();

        // LOGIN BUTTON
        loginButton.setOnAction(event -> {
            if (NumberValidator.isInteger(idField.getText())) {
                int id = Integer.parseInt(idField.getText());
                Teacher teacher = DatabaseDriver.logTeacherIn(id, passwordField.getText());
                if (teacher != null) {
                    AdministratorView.display();
                    stage.close();
                }
                else
                    AlertBox.display("Error Logging In", "Password incorrect.");
            }
            else
                AlertBox.display("Login Error", "ID must be a positive integer.");
        });

        // STUDENT LOGIN
        studentLoginButton.setOnAction(event -> {
            StudentLogin.display();
            stage.close();
        });

        // BUTTON HBOX
        buttonHBox.getChildren().add(loginButton);

        // TEXT FIELDS
        idField.setPromptText("Teacher ID");
        passwordField.setPromptText("Teacher Password");

        // VBOX
        vBox.getChildren().addAll(teacherLoginLabel, idField, passwordField, loginButton);

        // SCENE

        // STAGE
        stage.setScene(scene);
        stage.show();
    }
}
