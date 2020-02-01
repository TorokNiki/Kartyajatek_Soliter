package backEnd.services.game;

import backEnd.domain.Card;
import backEnd.domain.Rank;
import backEnd.services.factory.DeckFactory;
import frontEnd.Controller;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpsideDownPyramid {
    public List<Card> doubleDeck, deck, vastPile;
    public double orgSceneX, orgSceneY;
    public double orgTranslateX, orgTranslateY;

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

    public UpsideDownPyramid(Pane panel) {
        doubleDeck = new DeckFactory().doubleDeck();
        deck = new ArrayList<>();
        vastPile = new ArrayList<>();
        placeCardsOnBoard(panel);
    }

    public void recursiveTranslate(Card source, double newTranslateX, double newTranslateY) {
        Card cardOnIt = source.getCardOnIt();
        if (cardOnIt != null) {
            cardOnIt.setTranslateX(newTranslateX);
            cardOnIt.setTranslateY(newTranslateY);
            recursiveTranslate(cardOnIt, newTranslateX, newTranslateY);
        }
    }

    public void placeCardsOnBoard(Pane panel) {
        emptySpace(15, 2, panel);
        emptySpace(140, 10, panel);

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
            panel.getChildren().add(card);
        }
        Collections.shuffle(doubleDeck);
        int actual = 0;
        Card card = null;
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= i * 2 + 1; j++) {
                actual = placeCards(actual, i, j, panel);
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
                actual = placeCards(actual, i, j, panel);
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
                    showDeck(panel);
                }
            });
            panel.getChildren().add(doubleDeck.get(actual));

            actual++;
        }


    }

    private int placeCards(int actual, int i, int j, Pane panel) {
        doubleDeck.get(actual).setFitWidth(70);
        doubleDeck.get(actual).setFitHeight(110);
        doubleDeck.get(actual).setLayoutX((i * doubleDeck.get(actual).getFitWidth() + i * 10) + 5);
        doubleDeck.get(actual).setLayoutY(j * 25 + 110);
        doubleDeck.get(actual).setImage(doubleDeck.get(actual).getFront());
        doubleDeck.get(actual).setOnMousePressed(imageViewOnMousePressedEventHandler);
        doubleDeck.get(actual).setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
        panel.getChildren().add(doubleDeck.get(actual));
        return actual;
    }

    private void emptySpace(int y, int length, Pane panel) {
        for (int i = 0; i < length; i++) {
            ImageView empty = new ImageView();
            Image img = new Image("./resources/img/kuka.png");
            empty.setImage(img);
            empty.setFitWidth(70);
            empty.setLayoutX((i * empty.getFitWidth() + i * 10) + 5);
            empty.setLayoutY(y);
            empty.setPreserveRatio(true);
            panel.getChildren().add(empty);
        }
    }

    private void showDeck(Pane panel) {
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
                panel.getChildren().add(vastPile.get(i));
            }
        } else {
            for (int i = 0; i < vastPile.size(); i++) {
                deck.add(vastPile.get(i));
                deck.get(i).getBack();
                deck.get(i).setFitWidth(70);
                deck.get(i).setLayoutY(15);
                deck.get(i).setLayoutX(5);
                vastPile.remove(i);
                panel.getChildren().add(deck.get(i));
            }
        }
    }

}
