package frontEnd;

import backEnd.services.factory.Config;
import backEnd.services.game.OwnMenu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Config.loadProp();
        MainController mainController= new MainController(primaryStage);
//        MenuBar menuBar = OwnMenu.getMenuBar(primaryStage);
//        this.score = new TextField();
//        OwnMenu m = new OwnMenu();
//        MainController c = new MainController();
//        Pane first = c.upsideDownPyramid();
//        score=m.setTFScore(c.getScore(), c.getCurrentScore());
//        this.gameName = new Label();
//        m.setLabel(gameName,"Upside-Down Piramid (1/10)","#752777");
//        Group root = new Group(menuBar, score, gameName, first);
//        primaryStage.getIcons().add(new Image("./resources/img/ace.png"));
//        primaryStage.setTitle("Kártyajáték (Solitaire)");
//        primaryStage.setScene(new Scene(root, 800, 624));
//        primaryStage.setResizable(false);
//        primaryStage.show();
    }



}
