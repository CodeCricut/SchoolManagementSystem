package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class HelpStage {

    public static void display(){
        Label label = new Label();
        label.setText("For any inquiries, visit example.com");
        label.setFont(new Font("Arial", 18));

        VBox vBox = new VBox();
        vBox.getChildren().add(label);
        Scene scene = new Scene(vBox);
        Stage stage = new Stage();
        stage.setWidth(500);
        stage.setHeight(300);
        stage.setScene(scene);
        stage.show();
    }
}
