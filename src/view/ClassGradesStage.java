package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Assignment;
import model.Class;
import model.DatabaseDriver;


public class ClassGradesStage {

    private static Stage stage;
    private static Scene scene;
    private static VBox vbox;
    private static Label classLabel;
    private static TableView table;
    private static ObservableList assignments;

    ClassGradesStage(Class myClass){
        table = new TableView();
        classLabel = new Label();
        vbox = new VBox();
        scene = new Scene(vbox);
        stage = new Stage();

        classLabel.setText(myClass.getName());

        assignments = FXCollections.observableArrayList();
        assignments.addAll(DatabaseDriver.getClassAssignments(myClass.getId()));
        table.setItems(assignments);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));

        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("description"));

        TableColumn gradeCol = new TableColumn("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<Assignment, Double>("grade"));

        table.getColumns().addAll(nameCol, descriptionCol, gradeCol);

        vbox.getChildren().addAll(classLabel, table);
        stage.setScene(scene);
    }

    public static void display(){
        stage.show();
    }
}
