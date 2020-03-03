package frontEnd;

import backEnd.services.factory.Config;
import backEnd.services.game.Game;
import backEnd.services.game.Klondike;
import backEnd.services.game.UpsideDownPyramid;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainController {
    public Game upsideDownPyramid, threeShufflesAndADraw, scorpion, superScorpion, salicLaw, pyramid, laNivernaise, klondike, fortyThieves;
    public Game actualGame;
    private int currentScore, score;
    private String colour =Config.properties.getProperty("bacground");
    private String backColour = "-fx-background-color: "+ colour +";";
    private Label gameName;
    private TextField scoreText;
    private OwnMenu ownMenu;
    private Group root;
    private String name="Upside-Down Piramid (1/10)",coloure="#752777";

    public MainController(Stage primaryStage) {
        upsideDownPyramid = new UpsideDownPyramid(this);
        klondike=new Klondike(this);
        actualGame=klondike;
        this.scoreText = new TextField();
        this.ownMenu =new OwnMenu(primaryStage.widthProperty(),this);
        this.gameName=new Label();
        defaultSettings();
        this.root = new Group(this.ownMenu.getMenuBar(), scoreText, gameName, actualGame.getBoard());
        primaryStage.getIcons().add(new Image("./resources/img/ace.png"));
        primaryStage.setTitle("Kártyajáték (Solitaire)");
        primaryStage.setScene(new Scene(root, 800, 624));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void restartGame(){
        setCurrentScore(0);
        setScore(0);
        ownMenu.setTFScore(this.scoreText,getScore(),getCurrentScore());
        actualGame.restartGame(false);
    }
    public void restartTure(){
        this.name="Upside-Down Piramid (1/10)";
        setCurrentScore(0);
        setScore(0);
        ownMenu.setTFScore(this.scoreText,getScore(),getCurrentScore());
        ownMenu.setLabel(gameName,this.name,this.coloure);
        upsideDownPyramid.restartGame(true);
    }

    private void defaultSettings(){
        scoreText=ownMenu.setTFScore(this.scoreText,getScore(),getCurrentScore());
        ownMenu.setLabel(gameName,this.name,this.coloure);
    }
    public void goToNextGame(){
        actualGame.getBoard().getChildren().clear();
        klondike=new Klondike(this);
        actualGame=klondike;
        this.name="Klondike (2/10)";
        defaultSettings();
        //this.root = new Group(this.ownMenu.getMenuBar(), scoreText, gameName, actualGame.getBoard());






    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBackColour() {
        return backColour;
    }

    public Label getGameName() {
        return gameName;
    }

    public OwnMenu getOwnMenu() {
        return ownMenu;
    }

    public void setGameName(Label gameName) {
        this.gameName = gameName;
    }

    public TextField getScoreText() {
        return scoreText;
    }

    public void setScoreText(TextField scoreText) {
        this.scoreText = scoreText;
    }

    public void setBackColour(String color) {
        this.colour = color;
    }


}
