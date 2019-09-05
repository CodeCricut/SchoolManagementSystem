package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.DatabaseDriver;


public class AddSchool {

    private static Stage stage;
    private static Scene scene;
    private static VBox vbox;

    private static Label label;
    private static TextField schoolNameField;
    private static Button addSchoolButton;


    public static void display(){
        addSchoolButton = new Button("Add School");
        schoolNameField = new TextField();
        label = new Label("Add School");
        vbox = new VBox();
        scene = new Scene(vbox, 500, 500);
        stage = new Stage();

        // BUTTON
        addSchoolButton.setOnAction(event -> DatabaseDriver.addSchool(schoolNameField.getText()));

        // FIELDS
        schoolNameField.setPromptText("School Name");

        // LABEL
        label.setFont(new Font("arial", 18));

        // VBOX
        vbox.getChildren().addAll(label, schoolNameField, addSchoolButton);

        // SCENE

        // STAGE
        stage.setScene(scene);
        stage.show();
    }

}
