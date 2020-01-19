package frontEnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("config/upsideDownPyramid.fxml"));
        //primaryStage.getIcons().add(new Image("ace.png"));
        primaryStage.setTitle("Kártyajáték (Solitaire)");
        primaryStage.setScene(new Scene(root, 800, 624));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
