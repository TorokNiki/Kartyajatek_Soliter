package backEnd.services.game;

import backEnd.domain.ActionType;
import backEnd.domain.Card;
import backEnd.domain.Rank;
import backEnd.services.factory.DeckFactory;
import backEnd.services.game.mousegestures.MouseGestureKlondike;
import backEnd.services.game.mousegestures.MouseGesturesPyramid;
import backEnd.services.game.mousegestures.MouseGesturesUpsideDownPiramid;
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

public class Pyramid extends Game{
    public List<Card> simlpeDeck;
    public Stack<Card> deck, vastPile;
    public ImageView emptyDeck;
    public boolean restart;
    private ImageView[] acePlace;

    private List<Card> finalPozicionList;
    public Pyramid(MainController controller) {
        super(new DeckFactory().simpleImageDeck());
        mouseGestures = new MouseGesturesPyramid(controller);
        restart = false;
        start();
        ((MouseGesturesPyramid)mouseGestures).getDecks(deck,vastPile,emptyDeck,finalPozicionList);
    }

    private void start() {
        finalPozicionList = new ArrayList<>();
        acePlace=new ImageView[4];
        simlpeDeck = new ArrayList<>(fullDeck);
        deck = new Stack<>();
        vastPile = new Stack<>();
        placeCardsOnBoard();
    }


    @Override
    public void placeCardsOnBoard() {
        emptySpace(50, 2, board);
        ImageView empty = new ImageView();
        Image emptyimg = new Image("img/emptyCardSlot.png");
        empty.setId("col:");
        empty.setImage(emptyimg);
        empty.setFitWidth(70);
        empty.setLayoutX(700);
        empty.setLayoutY(140);
        empty.setPreserveRatio(true);
        empty.setOpacity(0.5);
        board.getChildren().add(empty);
        if (!restart) {
            Collections.shuffle(simlpeDeck);
        }
        int actual = 0;
        Card card = null;
        int space=0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i + 1; j++) {
                space=7-j;
                actual = placeCards(actual, i, j, board);

                if (card != null) {
                    card.setConnection(simlpeDeck.get(actual));
                }
                card = simlpeDeck.get(actual);
                actual++;
            }
            card=null;
        }
        for (int i = 0; i <= 23; i++) {
            deck.addElement(simlpeDeck.get(actual));
            simlpeDeck.get(actual).setLayoutX(((i * 0.002) * simlpeDeck.get(actual).getFitWidth()) + 30);
            simlpeDeck.get(actual).setLayoutY(50);
            simlpeDeck.get(actual).flippCard();
            simlpeDeck.get(actual).flippCard();
            board.getChildren().add(simlpeDeck.get(actual));

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

/*        emptySpace(15, 2, board);
        //emptySpace(140, 7, board);

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

        int index=0;
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
            empty2.setOpacity(0.5);
            acePlace[index]=empty;
            index++;
            board.getChildren().add(empty2);
        }
     */
    }

    private void showDeck() {
        ((MouseGesturesPyramid)mouseGestures).setActionType(ActionType.DECK);
        if (!deck.empty()) {
            int db = Math.min(deck.size(), 1);
            for (int i = 0; i < db; i++) {
                Card actual = deck.pop();
                actual.flippCard();
                mouseGestures.setMouseGestures(actual);
                actual.relocate(120, 50);
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
                    actual.relocate(((i * 0.002) * actual.getFitWidth()) + 30, 50);
                    deck.push(actual);
                } else {
                    actual.setInDeck(false);
                }
                i++;
            }
            emptyDeck.toFront();
        }
    }

    private int placeCards(int actual, int i, int j, Pane panel) {
        int space = ((j)+(7-i))*40;
        simlpeDeck.get(actual).setLayoutX(j*40+space+100);
        simlpeDeck.get(actual).setLayoutY((i * simlpeDeck.get(actual).getFitWidth()) +10);
        simlpeDeck.get(actual).flippCard();
        mouseGestures.setMouseGestures(simlpeDeck.get(actual));
        panel.getChildren().add(simlpeDeck.get(actual));
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
            simlpeDeck = new ArrayList<>(fullDeck);
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
        }
    }



}
