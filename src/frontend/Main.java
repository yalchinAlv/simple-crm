package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("leads_list.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("user-menu.fxml"));
        primaryStage.setTitle("SimpleCRM");

        primaryStage.setScene(new Scene(root, 920, 850));
        //primaryStage.setFullScreen(true);
        //primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) { launch(args); }
}
