package frontEnd;

import backEnd.services.database.SQLite;
import backEnd.services.factory.Config;
import javafx.application.Application;
import javafx.stage.Stage;


public class Starter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Config.loadProp();
        SQLite.selectAll();
        MainController mainController = new MainController(primaryStage);
    }


}
