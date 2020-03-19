package backEnd.services.game;

import backEnd.domain.Card;
import backEnd.domain.Rank;
import backEnd.services.factory.DeckFactory;
import backEnd.services.game.mousegestures.MouseGestures;
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

public class UpsideDownPyramid extends Game {
    public List<Card> doubleDeck;
    public Stack<Card> deck, vastPile;
    public ImageView emptyDeck;
    public MouseGestures mg;
    public boolean restart;
    public Card[] ase;
    private List<Card> finalPozicionList;



    public UpsideDownPyramid(MainController controller) {
        super(new DeckFactory().doubleDeck());
        mg = new MouseGestures(controller);
        restart = false;
        ase=new Card[8];
        start();
    }

    private void start() {
        finalPozicionList=new ArrayList<>();
        doubleDeck = new ArrayList<>(fullDeck);
        deck = new Stack<>();
        vastPile = new Stack<>();
        placeCardsOnBoard();
    }

    @Override
    public void restartGame(boolean fullrestart) {
        mg.db=0;
        restart = true;
        board.getChildren().clear();
        flippCardsonDefault();
        if (fullrestart) {
            restart=false;
            doubleDeck = new ArrayList<>(fullDeck);
        }
        deck = new Stack<>();
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
            c.setInDeck(false);
            //c.removeConnection();
        }
    }

    @Override
    public void placeCardsOnBoard() {
        emptySpace(15, 2, board);
        emptySpace(140, 10, board);
        for (int i = 0; i < 10; i++) {
            ImageView empty = new ImageView();
            Image emptyimg = new Image("img/emptyCardSlot.png");
            empty.setId("col:" + i);
            empty.setImage(emptyimg);
            empty.setFitWidth(70);
            empty.setLayoutX((i * empty.getFitWidth() + i * 10) + 5);
            empty.setLayoutY(140);
            empty.setPreserveRatio(true);
            empty.setOpacity(0);
            board.getChildren().add(empty);
        }
        if (!restart) {
            int index = 0;
            for (int i = 0; i < doubleDeck.size(); i++) {
                if (doubleDeck.get(i).getRank().equals(Rank.ACE)) {
                    doubleDeck.get(i).flippCard();
                    ase[index] = doubleDeck.get(i);
                    doubleDeck.remove(doubleDeck.get(i));
                    index++;
                }
            }
        }
        for (int i = 2; i < 10; i++) {
            Card card = ase[i - 2];
            card.setFinalPozicion(true);
            card.setLayoutX((i * card.getFitWidth() + i * 10) + 5);
            card.setLayoutY(10);
            card.setPreserveRatio(true);
            finalPozicionList.add(card);
            board.getChildren().add(card);
        }
        if (!restart){
        Collections.shuffle(doubleDeck);}
        int actual = 0;
        Card card = null;
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= i * 2 + 1; j++) {
                actual = placeCards(actual, i, j, board);
                if (card != null) {
                    card.setConnection(doubleDeck.get(actual));
                }
                card = doubleDeck.get(actual);
                actual++;
            }
            card = null;
        }
        for (int i = 5; i < 10; i++) {
            for (int j = 1; j <= (10 - i) * 2; j++) {
                actual = placeCards(actual, i, j, board);
                if (card != null) {
                    card.setConnection(doubleDeck.get(actual));
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
            board.getChildren().add(doubleDeck.get(actual));

            actual++;
        }
        emptyDeck = new ImageView();
        Image img = new Image("/img/emptyCardSlot.png");
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
        board.getChildren().add(emptyDeck);


    }

    private int placeCards(int actual, int i, int j, Pane panel) {
        doubleDeck.get(actual).setLayoutX((i * doubleDeck.get(actual).getFitWidth() + i * 10) + 5);
        doubleDeck.get(actual).setLayoutY(j * 25 + 110);
        doubleDeck.get(actual).flippCard();
        mg.MouseGestures(doubleDeck.get(actual));
        panel.getChildren().add(doubleDeck.get(actual));
        return actual;
    }

    private void emptySpace(int y, int length, Pane panel) {
        for (int i = 0; i < length; i++) {
            ImageView empty = new ImageView();
            Image img = new Image("img/kuka.png");
            empty.setImage(img);
            empty.setFitWidth(70);
            empty.setLayoutX((i * empty.getFitWidth() + i * 10) + 5);
            empty.setLayoutY(y);
            empty.setPreserveRatio(true);
            empty.toBack();
            panel.getChildren().add(empty);
        }
    }

    private void showDeck() {
        if (!deck.empty()) {
            int db = Math.min(deck.size(), 3);
            for (int i = 0; i < db; i++) {
                Card actual = deck.pop();
                actual.flippCard();
                mg.MouseGestures(actual);
                actual.relocate(80, 10);
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
                    actual.relocate(((i * 0.002) * actual.getFitWidth()) + 5, 10);
                    deck.push(actual);
                } else {
                    actual.setInDeck(false);
                }
                i++;
            }
            emptyDeck.toFront();
        }
    }

}
