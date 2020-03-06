package backEnd.services.game;

import backEnd.domain.Card;
import backEnd.services.factory.DeckFactory;
import frontEnd.MainController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Klondike extends Game {
    public ImageView emptyDeck;
    private MouseGestureKlondike mouseGestures;
    private List<Card> deck;
    private Stack<Card> deckRemain, vastPile;
    private boolean restart;

    public Klondike(MainController controller) {
        super(new DeckFactory().simpleImageDeck());
        mouseGestures = new MouseGestureKlondike(controller);
        restart = false;
        start();
    }

    private void start() {
        deck = new ArrayList<>(fullDeck);
        deckRemain = new Stack<>();
        vastPile = new Stack<>();
        placeCardsOnBoard();
    }


    @Override
    public void placeCardsOnBoard() {
        emptySpace(15, 2, board);
        emptySpace(140, 7, board);

        for (int i = 0; i < 10; i++) {
            ImageView empty = new ImageView();
            Image emptyimg = new Image("img/emptyCardSlot.png");
            empty.setId("col:" + i);
            empty.setImage(emptyimg);
            empty.setFitWidth(70);
            empty.setLayoutX((i * empty.getFitWidth() + i * 30) + 30);
            empty.setLayoutY(140);
            empty.setPreserveRatio(true);
            empty.setOpacity(0);
            board.getChildren().add(empty);
        }

        for (int i = 3; i < 7; i++) {
            ImageView empty = new ImageView();
            Image img = new Image("img/kuka.png");
            empty.setImage(img);
            empty.setFitWidth(70);
            empty.setLayoutX((i * empty.getFitWidth() + i * 30) + 30);
            empty.setLayoutY(15);
            empty.setPreserveRatio(true);
            empty.toBack();
            board.getChildren().add(empty);
            ImageView empty2 = new ImageView();
            Image emptyimg = new Image("img/emptyCardSlot.png");
            empty2.setId("row:" + i);
            empty2.setImage(emptyimg);
            empty2.setFitWidth(70);
            empty2.setLayoutX((i * empty.getFitWidth() + i * 30) + 30);
            empty2.setLayoutY(15);
            empty2.setPreserveRatio(true);
            empty2.setOpacity(0);
            board.getChildren().add(empty2);
        }
        if (!restart) {
            Collections.shuffle(deck);
        }
        int actual = 0;
        Card card = null;
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j <= i + 1; j++) {
                if (j > i) {
                    actual = placeCards(actual, i, j, board);
                } else {
                    actual = placeCards2(actual, i, j, board);
                }
                if (card != null) {
                    card.setConnection(deck.get(actual));
                }
                card = deck.get(actual);
                actual++;
            }
            card = null;
        }

        for (int i = 0; i <= 23; i++) {
            deckRemain.addElement(deck.get(actual));
            deck.get(actual).setLayoutX(((i * 0.002) * deck.get(actual).getFitWidth()) + 30);
            deck.get(actual).setLayoutY(10);
            deck.get(actual).flippCard();
            deck.get(actual).flippCard();
            board.getChildren().add(deck.get(actual));

            actual++;
        }
        emptyDeck = new ImageView();
        Image img = new Image("/img/emptyCardSlot.png");
        emptyDeck.setImage(img);
        emptyDeck.setFitWidth(85);
        emptyDeck.setFitHeight(110);
        emptyDeck.setLayoutX(30);
        emptyDeck.setLayoutY(10);
        emptyDeck.setOpacity(0);
        emptyDeck.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                showDeck();
            }
        });
        board.getChildren().add(emptyDeck);


    }

    private void showDeck() {
        if (!deckRemain.empty()) {
            int db = Math.min(deckRemain.size(), 1);
            for (int i = 0; i < db; i++) {
                Card actual = deckRemain.pop();
                actual.flippCard();
                mouseGestures.MouseGestures(actual);
                actual.relocate(120, 10);
                actual.toFront();
                actual.setInDeck(true);
                vastPile.push(actual);
            }
        } else {
            int i = 0;
            while (vastPile.size() != 0) {
                Card actual = vastPile.pop();
                if (!actual.isSticked() && actual.isInDeck()) {
                    actual.flippCard();
                    actual.relocate(((i * 0.002) * actual.getFitWidth()) + 30, 10);
                    deckRemain.push(actual);
                } else {
                    actual.setInDeck(false);
                }
                i++;
            }
            emptyDeck.toFront();
        }
    }

    private int placeCards(int actual, int i, int j, Pane panel) {
        deck.get(actual).setLayoutX((i * deck.get(actual).getFitWidth() + i * 30) + 30);
        deck.get(actual).setLayoutY(j * 12 + 130);
        deck.get(actual).flippCard();
        mouseGestures.MouseGestures(deck.get(actual));
        panel.getChildren().add(deck.get(actual));
        return actual;
    }

    private int placeCards2(int actual, int i, int j, Pane panel) {
        deck.get(actual).setLayoutX((i * deck.get(actual).getFitWidth() + i * 30) + 30);
        deck.get(actual).setLayoutY(j * 12 + 130);
        deck.get(actual).flippCard();
        deck.get(actual).flippCard();
        mouseGestures.MouseGestures(deck.get(actual));
        panel.getChildren().add(deck.get(actual));
        return actual;
    }

    private void emptySpace(int y, int length, Pane panel) {
        for (int i = 0; i < length; i++) {
            ImageView empty = new ImageView();
            Image img = new Image("img/kuka.png");
            empty.setImage(img);
            empty.setFitWidth(70);
            empty.setLayoutX((i * empty.getFitWidth() + i * 30) + 30);
            empty.setLayoutY(y);
            empty.setPreserveRatio(true);
            empty.toBack();
            panel.getChildren().add(empty);
        }
    }

    @Override
    public void restartGame(boolean fullrestart) {
        mouseGestures.db = 0;
        restart = true;
        board.getChildren().clear();
        flippCardsonDefault();
        if (fullrestart) {
            restart = false;
            deck = new ArrayList<>(fullDeck);
        }
        deckRemain = new Stack<>();
        vastPile = new Stack<>();
        placeCardsOnBoard();
    }

    public void flippCardsonDefault() {
        for (Card c : fullDeck) {
            c.setFaceup(false);
            c.setCardOnIt(null);
            c.setCardBeforeIt(null);
            c.setFinalPozicion(false);
            c.setSticked(false);
        }
    }

}
