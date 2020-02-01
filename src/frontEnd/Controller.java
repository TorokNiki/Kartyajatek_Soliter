package frontEnd;

import backEnd.services.game.UpsideDownPyramid;
import javafx.scene.layout.Pane;


public class Controller {
    public Pane upsideDownPyramid, threeShufflesAndADraw, scorpion, superScorpion, salicLaw, pyramid, laNivernaise, klondike, fortyThieves;
    public int currentScore, score;

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

    public Pane upsideDownPyramid() {
        upsideDownPyramid = new Pane();
        upsideDownPyramid.setStyle("-fx-background-color: lightblue;");
        upsideDownPyramid.setLayoutY(25);
        upsideDownPyramid.setPrefHeight(600);
        upsideDownPyramid.setPrefWidth(809);
        setCurrentScore(0);
        setScore(0);
        new UpsideDownPyramid(upsideDownPyramid);
        return upsideDownPyramid;
    }
}
