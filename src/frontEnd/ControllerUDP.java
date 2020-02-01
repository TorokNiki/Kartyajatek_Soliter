package frontEnd;

import backEnd.domain.Card;
import backEnd.domain.Rank;
import backEnd.services.factory.DeckFactory;
import backEnd.services.game.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerUDP implements Initializable {
    public Pane pnlGameSpace, pnlApplication;
    public Pane upsideDownPyramid, threeShufflesAndADraw, scorpion, superScorpion, salicLaw, pyramid, laNivernaise, klondike, fortyThieves;
    public MenuBar menubControls;
    public TextField textScore;
    public Scene application;
    public MenuItem menuClose, UndoLastMove, showScoreBoard, startNewTure, restartCurrentTure, startNewGame, endCurrentTure, autoFinish, changeCardLook, changeCardBackLook, changeBeckgroungColor, menuGameRoles;
    public double orgSceneX, orgSceneY;
    public double orgTranslateX, orgTranslateY;
    public int currentScore, score;
    public List<Card> doubleDeck, deck, vastPile;
    public ImageView deckHolder;
    public boolean firstStart;

    EventHandler<MouseEvent> imageViewOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((ImageView) (t.getSource())).getTranslateX();
                    orgTranslateY = ((ImageView) (t.getSource())).getTranslateY();
                }
            };
    EventHandler<MouseEvent> imageViewOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    Card source = (Card) (t.getSource());
                    recursiveTranslate(source, newTranslateX, newTranslateY);
                    source.setTranslateX(newTranslateX);
                    source.setTranslateY(newTranslateY);
                }
            };

    public void recursiveTranslate(Card source, double newTranslateX, double newTranslateY) {
        Card cardOnIt = source.getCardOnIt();
        if (cardOnIt != null) {
            cardOnIt.setTranslateX(newTranslateX);
            cardOnIt.setTranslateY(newTranslateY);
            recursiveTranslate(cardOnIt, newTranslateX, newTranslateY);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentScore = 0;
        score = 0;
//        deckHolder.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                showDeck();
//            }
//        });
//        startNewGame.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//               new Menu().startNext(actionEvent);
//              Stage currentStage = (Stage) pnlGameSpace.getScene().getWindow();
//              currentStage.close();
//            }
//        });
        firstStart = false;
        doubleDeck = new DeckFactory().doubleDeck();
        deck = new ArrayList<>();
        vastPile = new ArrayList<>();
        placeCardsOnBoard();
    }


    public void placeCardsOnBoard() {
        emptySpace(15, 2);
        emptySpace(140, 10);

        Card[] ase = new Card[8];
        int index = 0;
        for (int i = 0; i < doubleDeck.size(); i++) {
            if (doubleDeck.get(i).getRank().equals(Rank.ACE)) {
                ase[index] = doubleDeck.get(i);
                doubleDeck.remove(doubleDeck.get(i));
                index++;
            }
        }

        for (int i = 2; i < 10; i++) {
            Card card = ase[i - 2];
            card.setImage(card.getFront());
            card.setFitWidth(70);
            card.setLayoutX((i * card.getFitWidth() + i * 10) + 5);
            card.setLayoutY(10);
            card.setPreserveRatio(true);
            pnlGameSpace.getChildren().add(card);
        }
        if (!firstStart) {
            Collections.shuffle(doubleDeck);
            firstStart = true;
        }
        int actual = 0;
        Card card = null;
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= i * 2 + 1; j++) {
                actual = placeCards(actual, i, j);
                if (card != null) {
                    card.setCardOnIt(doubleDeck.get(actual));
                }
                card = doubleDeck.get(actual);
                actual++;
            }
            card = null;
        }
        for (int i = 5; i < 10; i++) {
            for (int j = 1; j <= (10 - i) * 2; j++) {
                actual = placeCards(actual, i, j);
                if (card != null) {
                    card.setCardOnIt(doubleDeck.get(actual));
                }
                card = doubleDeck.get(actual);
                actual++;
            }
            card = null;
        }
        for (int i = 0; i <= 40; i++) {
            deck.add(doubleDeck.get(actual));
            doubleDeck.get(actual).setFitWidth(70);
            doubleDeck.get(actual).setFitHeight(110);
            doubleDeck.get(actual).setLayoutX(((i * 0.005) * doubleDeck.get(actual).getFitWidth()) + 5);
            doubleDeck.get(actual).setLayoutY(10);
            doubleDeck.get(actual).setImage(doubleDeck.get(actual).getBack());
//            doubleDeck.get(actual).setOnMousePressed(imageViewOnMousePressedEventHandler);
//            doubleDeck.get(actual).setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
            doubleDeck.get(actual).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    showDeck();
                }
            });
            pnlGameSpace.getChildren().add(doubleDeck.get(actual));

            actual++;
        }


    }

    private int placeCards(int actual, int i, int j) {
        doubleDeck.get(actual).setFitWidth(70);
        doubleDeck.get(actual).setFitHeight(110);
        doubleDeck.get(actual).setLayoutX((i * doubleDeck.get(actual).getFitWidth() + i * 10) + 5);
        doubleDeck.get(actual).setLayoutY(j * 25 + 110);
        doubleDeck.get(actual).setImage(doubleDeck.get(actual).getFront());
        doubleDeck.get(actual).setOnMousePressed(imageViewOnMousePressedEventHandler);
        doubleDeck.get(actual).setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
        pnlGameSpace.getChildren().add(doubleDeck.get(actual));
        return actual;
    }

    private void emptySpace(int y, int length) {
        for (int i = 0; i < length; i++) {
            ImageView empty = new ImageView();
            Image img = new Image("./resources/img/kuka.png");
            empty.setImage(img);
            empty.setFitWidth(70);
            empty.setLayoutX((i * empty.getFitWidth() + i * 10) + 5);
            empty.setLayoutY(y);
            empty.setPreserveRatio(true);
            pnlGameSpace.getChildren().add(empty);
        }
    }

    private void showDeck() {
        if (deck.size() > 0) {
            for (int i = 0; i < 3; i++) {
                vastPile.add(deck.get(i));
                vastPile.get(i).getFront();
                vastPile.get(i).setOnMousePressed(imageViewOnMousePressedEventHandler);
                vastPile.get(i).setOnMousePressed(imageViewOnMouseDraggedEventHandler);
                vastPile.get(i).setFitWidth(70);
                vastPile.get(i).setLayoutY(15);
                vastPile.get(i).setLayoutX(55);
                deck.remove(i);
            }
        } else {
            for (int i = 0; i < vastPile.size(); i++) {
                deck.add(vastPile.get(i));
                deck.get(i).getBack();
                deck.get(i).setFitWidth(70);
                deck.get(i).setLayoutY(15);
                deck.get(i).setLayoutX(5);
                vastPile.remove(i);
            }
        }
    }


    public void nextGame(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("config/threeShuffelsAndADraw.fxml"));
        new Menu().startNext(actionEvent, fxmlLoader);

        Stage currentStage = (Stage) menubControls.getScene().getWindow();
        currentStage.close();

    }

    public void start(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("config/upsideDownPyramid.fxml"));
        new Menu().startNext(actionEvent, fxmlLoader);

        Stage currentStage = (Stage) menubControls.getScene().getWindow();
        currentStage.close();
        //Scene scene = SceneManager.GetActiveScene(); SceneManager.LoadScene(scene.name);
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void rules(ActionEvent actionEvent) {
        new Menu().openRools();
    }

    public void restart(ActionEvent actionEvent) {
        //placeCardsOnBoard();
    }
}
