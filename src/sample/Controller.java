package sample;

import game.Card;
import game.DeckFactory;
import game.Rank;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Pane pnlGameSpace, pnlApplication;
    public MenuBar menubControls;
    public TextField textScore;
    public ImageView imvDeck, imvWastePile, imvFoundation1, imvFoundation2, imvFoundation3, imvFoundation4, imvFoundation5, imvFoundation6, imvFoundation7, imvFoundation8;
    public MenuItem menuClose, UndoLastMove, showScoreBoard, startNewTure, restartCurrentTure, startNewGame, endCurrentTure, autoFinish, changeCardLook, changeCardBackLook, changeBeckgroungColor, menuGameRoles;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    int currentScore, score;
    List<Card> deck;
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

                    ((ImageView) (t.getSource())).setTranslateX(newTranslateX);
                    ((ImageView) (t.getSource())).setTranslateY(newTranslateY);
                }
            };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuClose.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        deck = new DeckFactory().doubleDeck();
        placeCardsOnBoard();
    }

    public void placeCardsOnBoard() {
        Card[] ase = new Card[8];
        int index = 0;
        for (int i = 0; i <deck.size() ; i++) {
            if (deck.get(i).getRank().equals(Rank.ACE)) {
                ase[index] = deck.get(i);
                deck.remove(deck.get(i));
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
        Collections.shuffle(deck);
        int actual=0;
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= i*2+1; j++) {
                deck.get(actual).setFitWidth(70);
                deck.get(actual).setFitHeight(110);
                deck.get(actual).setLayoutX((i * deck.get(actual).getFitWidth() + i * 10) + 5);
                deck.get(actual).setLayoutY(j*20+110);
                deck.get(actual).setImage(deck.get(actual).getFront());
                deck.get(actual).setOnMousePressed(imageViewOnMousePressedEventHandler);
                deck.get(actual).setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
                pnlGameSpace.getChildren().add(deck.get(actual));
                actual++;
            }
        }
        for (int i = 5; i < 10; i++) {
            for (int j = 1; j <= (10-i)*2; j++) {
                deck.get(actual).setFitWidth(70);
                deck.get(actual).setFitHeight(110);
                deck.get(actual).setLayoutX((i * deck.get(actual).getFitWidth() + i * 10) + 5);
                deck.get(actual).setLayoutY(j*20+110);
                deck.get(actual).setImage(deck.get(actual).getFront());
                deck.get(actual).setOnMousePressed(imageViewOnMousePressedEventHandler);
                deck.get(actual).setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
                pnlGameSpace.getChildren().add(deck.get(actual));
                actual++;
            }
        }
        for (int i = 0; i <= 40; i++) {
            deck.get(actual).setFitWidth(70);
            deck.get(actual).setFitHeight(110);
            deck.get(actual).setLayoutX((0 * deck.get(actual).getFitWidth() + 0 * 10) + 5);
            deck.get(actual).setLayoutY(10);
            deck.get(actual).setImage(deck.get(actual).getBack());
            deck.get(actual).setOnMousePressed(imageViewOnMousePressedEventHandler);
            deck.get(actual).setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
            pnlGameSpace.getChildren().add(deck.get(actual));
            actual++;
        }


    }


}
