package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.DatabaseDriver;

public class AddStudent {

    private static Stage stage;
    private static Scene scene;
    private static VBox vbox;

    private static Label label;
    private static TextField nameField, passwordField, schoolIDField;
    private static Button addStudentButton;


    public static void display(){
        addStudentButton = new Button("Add Student");
        nameField = new TextField();
        passwordField = new TextField();
        schoolIDField = new TextField();

        label = new Label("Add Student");
        vbox = new VBox();
        scene = new Scene(vbox, 500, 500);
        stage = new Stage();

        // BUTTON
        addStudentButton.setOnAction(event -> DatabaseDriver.addStudent(nameField.getText(),
                passwordField.getText(), Integer.parseInt(schoolIDField.getText())));

        // FIELDS
        nameField.setPromptText("Student Name");
        passwordField.setPromptText("Student Password");
        schoolIDField.setPromptText("School ID");

        // LABEL
        label.setFont(new Font("arial", 18));

        // VBOX
        vbox.getChildren().addAll(label, nameField,
                passwordField, schoolIDField, addStudentButton);

        // SCENE

        // STAGE
        stage.setScene(scene);
        stage.show();
    }
}
