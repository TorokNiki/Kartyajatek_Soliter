package backEnd.services.game;

import backEnd.domain.Card;
import backEnd.services.factory.Config;
import backEnd.services.factory.DeckFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.List;

public abstract class Game {
    protected final List<Card> fullDeck;
    protected Pane board;

    public Game(List<Card> fullDeck) {
        this.board = new Pane();
        this.fullDeck = fullDeck;
        board.setStyle("-fx-background-color: " + Config.properties.getProperty("bacground") + ";");
        board.setLayoutY(25);
        board.setPrefHeight(600);
        board.setPrefWidth(809);
    }

    public abstract void placeCardsOnBoard();

    public Pane getBoard() {
        return board;
    }
    public void cardLookChange(){
        if (!Config.tempFront.equals(Config.properties.getProperty("front"))){
            Config.properties.setProperty("front",Config.tempFront);
            Config.writeProp();
            for (Card c :fullDeck) {
                c.setFront(new Image(DeckFactory.DECK+Config.properties.getProperty("front")+"/" + c.getRank().getHelper() + c.getColor().getHelper()+".png"));
                if (c.isFaceup()){
                    c.setImage(c.getFront());
                }
            }
        }else if (!Config.tempBack.equals(Config.properties.getProperty("back"))){
            Config.properties.setProperty("back",Config.tempBack);
            Config.writeProp();
            for (Card c :fullDeck) {
                c.setBack(new Image(DeckFactory.BACKOFCARD+Config.properties.getProperty("back")+".png"));
                if (!c.isFaceup()){
                    c.setImage(c.getBack());
                }
            }
        }
    }
}
