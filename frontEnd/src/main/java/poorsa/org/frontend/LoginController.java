package poorsa.org.frontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LoginController {


    @FXML
    private Label in2;
    @FXML
    private Label sign;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void initialize() {

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
        username.setFocusTraversable(false);
        password.setFocusTraversable(false);
    }
}