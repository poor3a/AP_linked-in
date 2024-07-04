package poorsa.org.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;


public class CreateJobStatementController {

    @FXML
    TextField title;
    @FXML
    TextField workingState;
    @FXML
    TextField companyName;
    @FXML
    TextField companyAddress;
    @FXML
    CheckBox isWorking;
    @FXML
    DatePicker start;
    @FXML
    DatePicker end;
    @FXML
    ToggleGroup wt;
    @FXML
    TextArea description;
    @FXML
    Button confirm;
    @FXML
    Button back;

    @FXML
    public void initialize() {
        title.setOnMouseClicked(event -> Animations.getHeartbeat(title).play());
        workingState.setOnMouseClicked(event -> Animations.getHeartbeat(workingState).play());
        companyName.setOnMouseClicked(event -> Animations.getHeartbeat(companyName).play());
        companyAddress.setOnMouseClicked(event -> Animations.getHeartbeat(companyAddress).play());
        start.setOnMouseClicked(event -> Animations.getHeartbeat(start).play());
        end.setOnMouseClicked(event -> Animations.getHeartbeat(end).play());
        description.setOnMouseClicked(event -> Animations.getHeartbeat(description).play());
    }
    public void confirmOnAction() throws IOException {
        //#write here(post request for job statement)

        Animations.buttonAnimation(confirm);
        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        Scene scene = new Scene(root , 800 ,500);
        Stage stage = (Stage) confirm.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
        Animations.fadeScene(stage, scene);

    }
    public void backOnAction() {
        Animations.buttonAnimation(back);
    }
}
