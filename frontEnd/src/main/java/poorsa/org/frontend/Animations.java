package poorsa.org.frontend;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animations
{

    public static void buttonAnimation(Button createAccountButton) {
        createAccountButton.setDisable(true);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), createAccountButton);
        scaleTransition.setByX(0.1);
        scaleTransition.setByY(0.1);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }
    public static ScaleTransition getHeartbeat(Node node) {
        ScaleTransition heartbeat = new ScaleTransition(Duration.millis(75), node);
        heartbeat.setByX(0.15);
        heartbeat.setByY(0.15);
        heartbeat.setAutoReverse(true);
        heartbeat.setCycleCount(2);
        return heartbeat;
    }
    public static void fadeScene(Stage stage, Scene scene) {
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
