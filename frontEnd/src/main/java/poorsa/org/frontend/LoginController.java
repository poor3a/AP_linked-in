package poorsa.org.frontend;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import poorsa.org.frontend.models.*;

import java.io.IOException;

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
    private Button loginButton;
    @FXML
    private Button createAccountButton;
    @FXML
    private ImageView keypic;
    @FXML
    private ImageView emailpic;
    @FXML
    private Hyperlink forgotPassword;
    @FXML
    public void initialize() {
        password.setOnMouseEntered(event -> {
            keypic.setImage(new Image(getClass().getResource("/whitekey.png").toString()));
        });

        password.setOnMouseExited(event -> {
            keypic.setImage(new Image(getClass().getResource("/icons8-key-48.png").toString()));
        });
        username.setOnMouseEntered(event -> {
            emailpic.setImage(new Image(getClass().getResource("/white email.png").toString()));
        });
        username.setOnMouseExited(event -> {
            emailpic.setImage(new Image(getClass().getResource("/icons8-email-64.png").toString()));
        });
        username.setOnMouseClicked(event -> {
            Animations.getHeartbeat(username).play();
        });
        password.setOnMouseClicked(event -> {
            Animations.getHeartbeat(password).play();
        });

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

    public void loginButtonOnAction() {
        Animations.buttonAnimation(loginButton);
        createAccountButton.setDisable(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), event -> {
                    loginButton.setText("Logging in");
                }),
                new KeyFrame(Duration.millis(333), event -> {
                    loginButton.setText("Logging in.");
                }),
                new KeyFrame(Duration.millis(666), event -> {
                    loginButton.setText("Logging in..");
                }),
                new KeyFrame(Duration.millis(1000), event -> {
                    loginButton.setText("Logging in...");
                }),
                new KeyFrame(Duration.millis(1500), event -> {
                    loginButton.setText("Logging in...");
                })
        );
        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(event -> {
             //#
            //now check login conditions.
        });


    }
    public void createAccountButtonOnAction() throws IOException {
        Animations.buttonAnimation(createAccountButton);
        loginButton.setDisable(true);
        //#
        //check if username and password are not already exist, go to create account page.
        Parent root = FXMLLoader.load(getClass().getResource("createProfile.fxml"));
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
        Stage stage = (Stage) createAccountButton.getScene().getWindow();
        stage.setResizable(false);
        Animations.fadeScene(stage ,scene);

    }
    public void forgotPasswordOnAction() throws IOException {
        //#
//        Parent root = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
//        Scene scene = new Scene(root, 800, 500);
//        Stage stage = (Stage) forgotPassword.getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
        //i will make this later
    }


}