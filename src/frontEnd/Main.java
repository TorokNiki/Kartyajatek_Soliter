package frontEnd;

import backEnd.services.game.Menu;
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
    private Label gameName;
    private TextField score;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuBar menuBar = backEnd.services.game.Menu.getMenuBar(primaryStage);
        this.score = new TextField();
        Menu m = new Menu();
        Controller c = new Controller();
        Pane first = c.upsideDownPyramid();
        m.setTFScore(score, c.getScore(), c.getCurrentScore());
        this.gameName = new Label();
        m.setLabel(gameName);
        Group root = new Group(menuBar, score, gameName, first);
        primaryStage.getIcons().add(new Image("./resources/img/ace.png"));
        primaryStage.setTitle("Kártyajáték (Solitaire)");
        primaryStage.setScene(new Scene(root, 800, 624));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Label getGameName() {
        return gameName;
    }

    public void setGameName(Label gameName) {
        this.gameName = gameName;
    }

    public TextField getScore() {
        return score;
    }

    public void setScore(TextField score) {
        this.score = score;
    }


}
