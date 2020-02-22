package frontEnd;

import backEnd.services.game.UpsideDownPyramid;
import javafx.scene.layout.Pane;


public class Controller {
    public Pane upsideDownPyramid, threeShufflesAndADraw, scorpion, superScorpion, salicLaw, pyramid, laNivernaise, klondike, fortyThieves;
    public int currentScore, score;
    public String backColour = "-fx-background-color: lightblue;";

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

    public void setBackColour(String backColour) {
        this.backColour = backColour;
    }

    public Pane upsideDownPyramid() {
        upsideDownPyramid = new Pane();
        upsideDownPyramid.setStyle(getBackColour());
        upsideDownPyramid.setLayoutY(25);
        upsideDownPyramid.setPrefHeight(600);
        upsideDownPyramid.setPrefWidth(809);
        setCurrentScore(0);
        setScore(0);
        new UpsideDownPyramid(upsideDownPyramid);
        return upsideDownPyramid;
    }
}
