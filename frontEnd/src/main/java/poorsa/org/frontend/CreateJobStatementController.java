package poorsa.org.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;


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
    Label resultLabel;
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
    	String ti =  frontMethods.getTextField(title);
    	String WS =  frontMethods.getTextField(workingState);
    	String CN =  frontMethods.getTextField(companyName);
    	String CA =  frontMethods.getTextField(companyAddress);
    	boolean IW =  isWorking.isSelected();
    	String S =  frontMethods.getDate(start);
    	String E =  frontMethods.getDate(end);
    	String WT =  wt.getSelectedToggle().getUserData().toString();
    	String DE =  frontMethods.getTextArea(description);
    	try {
			URL url = new URL(frontMethods.URLFirstPart + "jobStatement");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			JSONObject json = new JSONObject();
			json.put("title", ti);
			json.put("workingState", WS);
			json.put("companyName", CN);
			json.put("companyAddress", CA);
			json.put("workingType", WT);
			json.put("isWorking", IW);
			json.put("start", S);
			json.put("end", E);
			json.put("description", DE);
			String token = frontMethods.getToken();
			connection.setRequestProperty("Authorization", token);
			frontMethods.sendRequest(connection, json.toString());
			String response = frontMethods.getResponse(connection);
			if (response.contains("Error")) {
				resultLabel.setText(response.toString());
                Animations.buttonAnimation(confirm);
                Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                Scene scene = new Scene(root , 800 ,500);
                Stage stage = (Stage) confirm.getScene().getWindow();
                scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
                Animations.fadeScene(stage, scene);
			} else {
				resultLabel.setText("Job statement created successfully");
			}
		} catch (Exception e) {
			resultLabel.setText("Something went wrong with the server");
		}

    }
    public void backOnAction() {
        Animations.buttonAnimation(back);
    }
}
