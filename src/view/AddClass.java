package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.DatabaseDriver;

public class AddClass {

    private static Stage stage;
    private static Scene scene;
    private static VBox vbox;

    private static Label label;
    private static TextField classNameField, teacherIDField;
    private static Button addClassButton;


    public static void display(){
        addClassButton = new Button("Add Class");
        classNameField = new TextField();
        teacherIDField = new TextField();
        label = new Label("Add Class");
        vbox = new VBox();
        scene = new Scene(vbox, 500, 500);
        stage = new Stage();

        // BUTTON
        addClassButton.setOnAction(event -> DatabaseDriver.addClass(classNameField.getText(),
                Integer.parseInt(teacherIDField.getText())));

        // FIELDS
        classNameField.setPromptText("Class Name");
        teacherIDField.setPromptText("Teacher ID");

        // LABEL
        label.setFont(new Font("arial", 18));

        // VBOX
        vbox.getChildren().addAll(label, classNameField,
                teacherIDField, addClassButton);

        // SCENE

        // STAGE
        stage.setScene(scene);
        stage.show();
    }
}
