package backEnd.services.game;

import backEnd.domain.Card;
import backEnd.domain.Rank;
import backEnd.services.factory.DeckFactory;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class UpsideDownPyramid {
    public List<Card> doubleDeck;
    public Stack<Card> deck, vastPile;
    public double orgSceneX, orgSceneY;
    public double orgTranslateX, orgTranslateY;
    public int index;

    EventHandler<MouseEvent> imageViewOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Card source = (Card) (t.getSource());
                    if (source.isFaceup()) {
                        orgSceneX = t.getSceneX();
                        orgSceneY = t.getSceneY();

                        orgTranslateX = source.getTranslateX();
                        orgTranslateY = source.getTranslateY();
                    }
                }
            };
    EventHandler<MouseEvent> imageViewOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Card source = (Card) (t.getSource());
                    if (source.isFaceup()) {
                        double offsetX = t.getSceneX() - orgSceneX;
                        double offsetY = t.getSceneY() - orgSceneY;
                        double newTranslateX = orgTranslateX + offsetX;
                        double newTranslateY = orgTranslateY + offsetY;

                        recursiveTranslate(source, newTranslateX, newTranslateY);
                        source.setTranslateX(newTranslateX);
                        source.setTranslateY(newTranslateY);
                    }
                }
            };

    public UpsideDownPyramid(Pane panel) {
        doubleDeck = new DeckFactory().doubleDeck();
        deck = new Stack<>();
        vastPile = new Stack<>();
        index = 0;
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
            card.flippCard();
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
            deck.addElement(doubleDeck.get(actual));
            doubleDeck.get(actual).setLayoutX(((i * 0.002) * doubleDeck.get(actual).getFitWidth()) + 5);
            doubleDeck.get(actual).setLayoutY(10);
            doubleDeck.get(actual).flippCard();
            doubleDeck.get(actual).flippCard();
            panel.getChildren().add(doubleDeck.get(actual));

            actual++;
        }
        ImageView emptyDeck = new ImageView();
        Image img = new Image("/resources/img/emptyCardSlot.png");
        emptyDeck.setImage(img);
        emptyDeck.setFitWidth(85);
        emptyDeck.setFitHeight(110);
        emptyDeck.setLayoutX(5);
        emptyDeck.setLayoutY(10);
        emptyDeck.setOpacity(0);
        emptyDeck.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                showDeck();
            }
        });
        panel.getChildren().add(emptyDeck);


    }

    private int placeCards(int actual, int i, int j, Pane panel) {
        doubleDeck.get(actual).setLayoutX((i * doubleDeck.get(actual).getFitWidth() + i * 10) + 5);
        doubleDeck.get(actual).setLayoutY(j * 25 + 110);
        doubleDeck.get(actual).flippCard();
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

    private void showDeck() {
        if (!deck.empty()) {
            int db = Math.min(deck.size(), 3);
            for (int i = 0; i < db; i++) {
                Card actual = deck.pop();
                actual.flippCard();
                actual.setOnMousePressed(imageViewOnMousePressedEventHandler);
                actual.setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
                actual.setLayoutY(10);
                actual.setX(80);
                vastPile.push(actual);
            }
        } else {
            int i =0;
            while (vastPile.size()!=0){
                Card actual = vastPile.pop();
                actual.flippCard();
                actual.setX(((i * 0.002) * actual.getFitWidth()) + 5);
                actual.setLayoutY(10);
                deck.push(actual);
                i++;
            }
        }
    }

}
