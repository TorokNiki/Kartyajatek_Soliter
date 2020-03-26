package backEnd.services.game;

import backEnd.domain.Card;
import backEnd.services.factory.Asset;
import backEnd.services.factory.Config;
import backEnd.services.factory.DeckFactory;
import backEnd.services.game.mousegestures.MouseGestures;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.List;

public abstract class Game {
    protected final List<Card> fullDeck;

    public MouseGestures getMouseGestures() {
        return mouseGestures;
    }

    protected MouseGestures mouseGestures;
    protected Pane board;

    public Game(List<Card> fullDeck) {
        this.board = new Pane();
        this.fullDeck = fullDeck;
        Background background= new Background(
                new BackgroundImage(
                        new Image("/img/bg/"+Config.properties.getProperty("bacground")),
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(300,300,
                                false,false,false,false)
                )
        );
        board.setBackground(background);
        //board.setStyle("-fx-background-color: " + Config.properties.getProperty("bacground") + ";");
        board.setLayoutY(25);
        board.setPrefHeight(600);
        board.setPrefWidth(809);
    }

    public abstract void placeCardsOnBoard();
    public abstract void restartGame(boolean fullrestart);

    public Pane getBoard() {
        return board;
    }
    public void cardLookChange(){
        if (!Config.tempFront.equals(Config.properties.getProperty("front"))){
            Config.properties.setProperty("front",Config.tempFront);
            Config.writeProp();
            Asset.Asset();
            for (Card c :fullDeck) {
                c.setFront(Asset.getAssets().getOrDefault(c.getRank().helper+""+ c.getColor().helper,new Image(DeckFactory.DECK+Config.properties.getProperty("front")+"/" + c.getRank().getHelper() + c.getColor().getHelper()+".png")));
                if (c.isFaceup()){
                    c.setImage(c.getFront());
                }
            }
        }else if (!Config.tempBack.equals(Config.properties.getProperty("back"))){
            Config.properties.setProperty("back",Config.tempBack);
            Config.writeProp();
            Asset.Asset();
            for (Card c :fullDeck) {
                c.setBack(Asset.getAssets().getOrDefault("back",new Image(DeckFactory.BACKOFCARD+Config.properties.getProperty("back")+".png")));
                if (!c.isFaceup()){
                    c.setImage(c.getBack());
                }
            }
        }
    }
    public void backgoundChange(){
        if (!Config.tempBackground.equals(Config.properties.getProperty("bacground"))){
            Config.properties.setProperty("bacground",Config.tempBackground);
            Config.writeProp();
            Background background= new Background(
                    new BackgroundImage(
                            new Image("/img/bg/"+Config.properties.getProperty("bacground")),
                            BackgroundRepeat.REPEAT,
                            BackgroundRepeat.REPEAT,
                            BackgroundPosition.DEFAULT,
                            new BackgroundSize(300,300,
                                    false,false,false,false)
                    )
            );
            board.setBackground(background);
            //board.setStyle("-fx-background-color: " + Config.properties.getProperty("bacground") + ";");

            }
    }
}
