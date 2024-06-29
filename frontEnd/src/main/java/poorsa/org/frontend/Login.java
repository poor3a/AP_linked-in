package poorsa.org.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        primaryStage.setResizable(false);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}
