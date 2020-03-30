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
    protected MouseGestures mouseGestures;
    protected Pane board;

    public Game(List<Card> fullDeck) {
        this.board = new Pane();
        this.fullDeck = fullDeck;
        if (Config.properties.getProperty("bacground").equals("image")) {
            Background background = new Background(
                    new BackgroundImage(
                            new Image("/img/bg/" + Config.properties.getProperty("bacgroundimage")),
                            BackgroundRepeat.REPEAT,
                            BackgroundRepeat.REPEAT,
                            BackgroundPosition.DEFAULT,
                            new BackgroundSize(300, 300,
                                    false, false, false, false)
                    )
            );
            board.setBackground(background);
        } else if (Config.properties.getProperty("bacground").equals("color")) {
            board.setStyle("-fx-background-color: " + Config.properties.getProperty("bacgroundcolor") + ";");
        }
        board.setLayoutY(25);
        board.setPrefHeight(610);
        board.setPrefWidth(809);
    }

    public MouseGestures getMouseGestures() {
        return mouseGestures;
    }

    public abstract void placeCardsOnBoard();

    public abstract void restartGame(boolean fullrestart);

    public Pane getBoard() {
        return board;
    }

    public void cardLookChange() {
        if (!Config.tempFront.equals(Config.properties.getProperty("front"))) {
            Config.properties.setProperty("front", Config.tempFront);
            Config.writeProp();
            Asset.Asset();
            for (Card c : fullDeck) {
                c.setFront(Asset.getAssets().getOrDefault(c.getRank().helper + "" + c.getColor().helper, new Image(DeckFactory.DECK + Config.properties.getProperty("front") + "/" + c.getRank().getHelper() + c.getColor().getHelper() + ".png")));
                if (c.isFaceup()) {
                    c.setImage(c.getFront());
                }
            }
        } else if (!Config.tempBack.equals(Config.properties.getProperty("back"))) {
            Config.properties.setProperty("back", Config.tempBack);
            Config.writeProp();
            Asset.Asset();
            for (Card c : fullDeck) {
                c.setBack(Asset.getAssets().getOrDefault("back", new Image(DeckFactory.BACKOFCARD + Config.properties.getProperty("back") + ".png")));
                if (!c.isFaceup()) {
                    c.setImage(c.getBack());
                }
            }
        }
    }

    public void backgoundChange() {
        if (Config.tempBackground.equals("image")) {
            if (!Config.tempBackgroundimage.equals(Config.properties.getProperty("bacgroundimage"))||Config.properties.getProperty("bacground").equals("color")) {
                Config.properties.setProperty("bacground", Config.tempBackground);
                Config.properties.setProperty("bacgroundimage", Config.tempBackgroundimage);
                Config.writeProp();
                Background background = new Background(
                        new BackgroundImage(
                                new Image("/img/bg/" + Config.properties.getProperty("bacgroundimage")),
                                BackgroundRepeat.REPEAT,
                                BackgroundRepeat.REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(300, 300,
                                        false, false, false, false)
                        )
                );
                board.setBackground(background);
            }
        } else if (Config.tempBackground.equals("color")) {
            if (!Config.tempBackgroundcolor.equals(Config.properties.getProperty("bacgroundcolor"))||Config.properties.getProperty("bacground").equals("image")) {
                Config.properties.setProperty("bacgroundcolor", Config.tempBackgroundcolor);
                Config.properties.setProperty("bacground", Config.tempBackground);
                Config.writeProp();
            }
            //board.setBackground(null);
            board.setStyle("-fx-background-color: " + Config.properties.getProperty("bacgroundcolor") + ";");
        }

    }
}
