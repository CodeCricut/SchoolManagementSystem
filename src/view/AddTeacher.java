package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.DatabaseDriver;

public class AddTeacher {

    private static Stage stage;
    private static Scene scene;
    private static VBox vbox;

    private static Label label;
    private static TextField nameField, emailField, schoolIDField;
    private static Button addTeacherButton;


    public static void display(){
        addTeacherButton = new Button("Add Teacher");
        nameField = new TextField();
        emailField = new TextField();
        schoolIDField = new TextField();

        label = new Label("Add Teacher");
        vbox = new VBox();
        scene = new Scene(vbox, 500, 500);
        stage = new Stage();

        // BUTTON
        addTeacherButton.setOnAction(event -> DatabaseDriver.addTeacher(nameField.getText(),
                emailField.getText(), Integer.parseInt(schoolIDField.getText())));

        // FIELDS
        nameField.setPromptText("Teacher Name");
        emailField.setPromptText("Email (optional)");
        schoolIDField.setPromptText("School ID");

        // LABEL
        label.setFont(new Font("arial", 18));

        // VBOX
        vbox.getChildren().addAll(label, nameField,
                emailField, schoolIDField, addTeacherButton);

        // SCENE

        // STAGE
        stage.setScene(scene);
        stage.show();
    }
}
