package frontEnd;

import backEnd.services.factory.Config;
import javafx.application.Application;
import javafx.stage.Stage;


public class Indito extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Config.loadProp();
        MainController mainController= new MainController(primaryStage);
    }



}
