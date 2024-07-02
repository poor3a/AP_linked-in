package poorsa.org.frontend;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import poorsa.org.frontend.models.Profile;

import java.io.File;
import java.io.IOException;



public class CreateProfileController
{


    @FXML
    Label in2;
    @FXML
    Label sign;
    @FXML
    Button confirm;
    @FXML
    Button back;
    @FXML
    TextField firstname;
    @FXML
    TextField lastname;
    @FXML
    TextField additionalName;
    @FXML
    DatePicker birthDate;
    @FXML
    TextArea title;
    @FXML
    TextField place;
    @FXML
    TextField career;
    @FXML
    TextField jobAiming;
    @FXML
    Button bg_image;
    @FXML
    Button profile_image;
    @FXML
    StackPane pane;




    @FXML
    public void initialize()
    {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), event -> {
                }),
                new KeyFrame(Duration.millis(1600), event -> {
                    in2.setOpacity(1);
                }),
                new KeyFrame(Duration.millis(1625), event -> {
                    in2.setOpacity(.75);
                }),
                new KeyFrame(Duration.millis(1750), event -> {
                    in2.setOpacity(.5);
                    sign.translateXProperty().set(0);
                }),
                new KeyFrame(Duration.millis(1875), event -> {
                    in2.setOpacity(.25);
                    sign.translateXProperty().set(-5);
                }),
                new KeyFrame(Duration.millis(2000), event -> {
                    in2.setOpacity(0);
                    sign.translateXProperty().set(-10);
                }),
                new KeyFrame(Duration.millis(2125), event -> {
                    sign.translateXProperty().set(-20);
                }),
                new KeyFrame(Duration.millis(2250), event -> {
                    sign.translateXProperty().set(-24);
                }),
                new KeyFrame(Duration.millis(3000), event -> {
                })
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        profile_image.getStyleClass().clear();
        profile_image.setTextFill(Paint.valueOf("white"));
        profile_image.setFont(javafx.scene.text.Font.font("Comic Sans MS", 14));
        profile_image.setBorder(new Border(new BorderStroke(Paint.valueOf("white"), BorderStrokeStyle.SOLID, new CornerRadii(50), new BorderWidths(2))));
        profile_image.setTextAlignment(TextAlignment.CENTER);
        profile_image.setPadding(new Insets(0,0,0,15));
        profile_image.setShape(new Circle(50));


        bg_image.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                bg_image.getStyleClass().clear();
                bg_image.setText("");
                bg_image.setBackground(new Background(backgroundImage));
            }
        });

       profile_image.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                BackgroundImage profileImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

                profile_image.setBackground(new Background(profileImage));
                profile_image.setText("");
                profile_image.requestLayout();
                // Center the button in the StackPane

    }
});
    }

    public void confirmOnAction() throws IOException {
        Animations.buttonAnimation(confirm);
        Profile newProfile = new Profile(firstname.getText(), lastname.getText(), additionalName.getText(), birthDate.getValue().toString(), "", "", title.getText(), place.getText(), career.getText(), jobAiming.getText());
        Parent root = FXMLLoader.load(getClass().getResource("contactInfo.fxml"));
        Stage stage = (Stage) confirm.getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
        Animations.fadeScene(stage, scene);
    }
    public void backOnAction() throws IOException {
        Animations.buttonAnimation(back);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root, 800, 500);
        Stage stage = (Stage) confirm.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Animations.fadeScene(stage, scene);
    }




}
