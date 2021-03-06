package backEnd.services.game;

import backEnd.domain.Card;
import backEnd.domain.PyramidCard;
import backEnd.services.factory.DeckFactory;
import backEnd.services.game.mousegestures.MouseGesturesPyramid;
import frontEnd.MainController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Pyramid extends Game {
    public List<Card> simlpeDeck;
    public Stack<PyramidCard> deck, vastPile;
    public ImageView emptyDeck;
    public boolean restart;
    public PyramidCard[][] connections;
    public List<PyramidCard> pyramid;

    private ImageView finalPozicion;

    public Pyramid(MainController controller) {
        super(new DeckFactory().pyramidImageDeck());
        mouseGestures = new MouseGesturesPyramid(controller);
        restart = false;
        start();

    }

    private void start() {
        connections = new PyramidCard[7][7];
        simlpeDeck = new ArrayList<>(fullDeck);
        deck = new Stack<>();
        vastPile = new Stack<>();
        pyramid = new ArrayList<>();
        placeCardsOnBoard();
        ((MouseGesturesPyramid) mouseGestures).getDecks(deck, vastPile, emptyDeck, finalPozicion, pyramid);
    }

    @Override
    public void placeCardsOnBoard() {
        emptySpace(board);
        finalPozicion = new ImageView();
        Image emptyimg = new Image("/img/emptyCardSlot.png");
        finalPozicion.setImage(emptyimg);
        finalPozicion.setFitWidth(70);
        finalPozicion.setFitHeight(100);
        finalPozicion.setLayoutX(700);
        finalPozicion.setLayoutY(140);
        finalPozicion.setId("row:");
        finalPozicion.setOpacity(0);
        board.getChildren().add(finalPozicion);
        if (!restart) {
            Collections.shuffle(simlpeDeck);
        }
        int actual = 0;
        Card card = null;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i + 1; j++) {

                placeCards(actual, i, j, board);

                if (card != null) {
                    connections[i][j] = (PyramidCard) simlpeDeck.get(actual);
                } else {
                    connections[i][j] = null;
                }
                card = simlpeDeck.get(actual);
                connections[i][j] = (PyramidCard) simlpeDeck.get(actual);
                actual++;
            }
            card = null;
        }
        for (int i = 0; i < connections.length; i++) {
            for (int j = 0; j < connections[i].length; j++) {
                if (connections[i][j] != null) {
                    pyramid.add(connections[i][j]);
                    connections[i][j].setPoint((connections.length - i) * 9);
                    if (i + 1 <= 6) {
                        connections[i][j].setConnection(connections[i + 1][j]);
                        connections[i][j].setConnection2(connections[i + 1][j + 1]);

                    }
                }
            }
        }

        for (int i = 0; i <= 23; i++) {
            deck.addElement((PyramidCard) simlpeDeck.get(actual));
            simlpeDeck.get(actual).setLayoutX(((i * 0.002) * simlpeDeck.get(actual).getFitWidth()) + 30);
            simlpeDeck.get(actual).setLayoutY(50);
            simlpeDeck.get(actual).flippCard();
            simlpeDeck.get(actual).flippCard();
            board.getChildren().add(simlpeDeck.get(actual));

            actual++;
        }
        PyramidCard cardFromDec = deck.pop();
        cardFromDec.flippCard();
        mouseGestures.setMouseGestures(cardFromDec);
        cardFromDec.setInDeck(true);

        vastPile.push((PyramidCard) cardFromDec);

        emptyDeck = new ImageView();
        emptyDeck.setImage(emptyimg);
        emptyDeck.setFitWidth(70);
        emptyDeck.setFitHeight(100);
        emptyDeck.setLayoutX((emptyDeck.getFitWidth() + 60));
        emptyDeck.setLayoutY(50);
        emptyDeck.setOpacity(0);
        emptyDeck.setId("col");
        board.getChildren().add(emptyDeck);

    }

    private void placeCards(int actual, int i, int j, Pane panel) {
        int space = ((j) + (7 - i)) * 40;
        simlpeDeck.get(actual).setLayoutX(j * 40 + space + 100);
        simlpeDeck.get(actual).setLayoutY((i * simlpeDeck.get(actual).getFitWidth()) + 10);
        simlpeDeck.get(actual).flippCard();
        mouseGestures.setMouseGestures(simlpeDeck.get(actual));
        panel.getChildren().add(simlpeDeck.get(actual));
    }

    private void emptySpace(Pane panel) {
        for (int i = 0; i < 2; i++) {
            ImageView empty = new ImageView();
            Image img = new Image("img/kuka.png");
            empty.setImage(img);
            empty.setFitWidth(70);
            empty.setLayoutX((i * empty.getFitWidth() + i * 30) + 30);
            empty.setLayoutY(50);
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
        if (fullrestart) {
            restart = false;
            simlpeDeck = new ArrayList<>(fullDeck);
        }
        flippCardsonDefault();
        deck = new Stack<>();
        vastPile = new Stack<>();
        pyramid = new ArrayList<>();
        placeCardsOnBoard();
        ((MouseGesturesPyramid) mouseGestures).getDecks(deck, vastPile, emptyDeck, finalPozicion, pyramid);
    }

    public void flippCardsonDefault() {
        for (Card c : fullDeck) {
            PyramidCard p = ((PyramidCard) c);
            p.setFaceup(false);
            p.setCardOnIt(null);
            p.setCardBeforeIt(null);
            p.setFinalPozicion(false);
            p.setSticked(false);
            p.setInDeck(false);
            p.setVaistpile(false);
            p.setCardBeforeIt2(null);
            p.setCardOnIt2(null);

        }
    }
}
