package poorsa.org.frontend;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class CreateAccountController
{
    @FXML
    TextField username;
    @FXML
    TextField email;
    @FXML
    TextField password;
    @FXML
    TextField passwordRepeat;
    @FXML
    ImageView keypic;
    @FXML
    ImageView emailpic;
    @FXML
    ImageView keypic1;
    @FXML
    ImageView emailpic1;
    @FXML
    Label in2;
    @FXML
    Label sign;
    @FXML
    Button createButton;
    @FXML
    Button backButton;


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
        username.setFocusTraversable(false);
        password.setFocusTraversable(false);
        username.setOnAction(event -> {
            Animations.getHeartbeat(username);
        });
        email.setOnAction(event -> {
            Animations.getHeartbeat(email);
        });
        password.setOnAction(event -> {
            Animations.getHeartbeat(password);
        });
        passwordRepeat.setOnAction(event -> {
            Animations.getHeartbeat(passwordRepeat);
        });
        password.setOnMouseEntered(event -> {
            keypic.setImage(new Image(getClass().getResource("/whitekey.png").toString()));
        });
        password.setOnMouseExited(event -> {
            keypic.setImage(new Image(getClass().getResource("/icons8-key-48.png").toString()));
        });
        username.setOnMouseEntered(event -> {
            emailpic.setImage(new Image(getClass().getResource("/icons8-username-white30.png").toString()));
        });
        username.setOnMouseExited(event -> {
            emailpic.setImage(new Image(getClass().getResource("/icons8-username-30.png").toString()));
        });
        email.setOnMouseEntered(event -> {
            emailpic1.setImage(new Image(getClass().getResource("/white email.png").toString()));
        });
        email.setOnMouseExited(event -> {
            emailpic1.setImage(new Image(getClass().getResource("/icons8-email-64.png").toString()));
        });
        passwordRepeat.setOnMouseEntered(event -> {
            keypic1.setImage(new Image(getClass().getResource("/whitekey.png").toString()));
        });
        passwordRepeat.setOnMouseExited(event -> {
            keypic1.setImage(new Image(getClass().getResource("/icons8-key-48.png").toString()));
        });
    }

    public void createAccountButtonOnAction() {
        Animations.buttonAnimation(createButton);

    }
    public void backButtonOnAction() throws IOException {
        Animations.buttonAnimation(backButton);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root, 800, 500);
        Stage stage = (Stage) createButton.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        FadeTransition ftOut = new FadeTransition(Duration.millis(500), stage.getScene().getRoot());
        ftOut.setFromValue(1.0);
        ftOut.setToValue(0.0);

        ftOut.play();
        ftOut.setOnFinished(event ->
        {
            stage.setScene(scene);
            scene.getRoot().setOpacity(0);
            stage.show();
            FadeTransition ftIn = new FadeTransition(Duration.millis(500), scene.getRoot());
            ftIn.setFromValue(0.0);
            ftIn.setToValue(1.0);
            ftIn.play();
        });

    }



}
