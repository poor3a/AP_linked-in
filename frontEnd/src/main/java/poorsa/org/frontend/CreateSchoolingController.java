package poorsa.org.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateSchoolingController {
    @FXML
    TextField schoolName;
    @FXML
    TextField fieldOfStudy;
    @FXML
    TextField grade;
    @FXML
    TextField degree;
    @FXML
    DatePicker start;
    @FXML
    DatePicker end;
    @FXML
    TextArea activities;
    @FXML
    TextArea description;
    @FXML
    Button confirm;
    @FXML
    Button back;

    @FXML
    public void initialize()
    {
        schoolName.setOnMouseClicked(event -> Animations.getHeartbeat(schoolName).play());
        fieldOfStudy.setOnMouseClicked(event -> Animations.getHeartbeat(fieldOfStudy).play());
        grade.setOnMouseClicked(event -> Animations.getHeartbeat(grade).play());
        degree.setOnMouseClicked(event -> Animations.getHeartbeat(degree).play());
        start.setOnMouseClicked(event -> Animations.getHeartbeat(start).play());
        end.setOnMouseClicked(event -> Animations.getHeartbeat(end).play());
        activities.setOnMouseClicked(event -> Animations.getHeartbeat(activities).play());
        description.setOnMouseClicked(event -> Animations.getHeartbeat(description).play());
    }
    public void confirmOnAction() throws IOException {
        //#write here(post request for schooling)
        Animations.buttonAnimation(confirm);
        Parent root = FXMLLoader.load(getClass().getResource("CreateJobStatement.fxml"));
        Scene scene = new Scene(root , 800 ,500);
        Stage stage = (Stage) confirm.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
        Animations.fadeScene(stage, scene);
    }
    public void backOnAction() throws IOException {
        Animations.buttonAnimation(back);
        Parent root = FXMLLoader.load(getClass().getResource("contactInfo.fxml"));
        Scene scene = new Scene(root, 800, 500);
        Stage stage = (Stage) confirm.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
        Animations.fadeScene(stage, scene);
    }
}