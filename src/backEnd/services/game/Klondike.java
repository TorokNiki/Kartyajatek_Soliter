package backEnd.services.game;

import backEnd.domain.Card;
import backEnd.domain.Rank;
import backEnd.services.factory.DeckFactory;
import frontEnd.MainController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Klondike extends Game {
    private MouseGestures mouseGestures;
    private List<Card> deck;
    private Stack<Card> deckRemain, vastPile;
    private boolean restart;

    public Klondike(MainController controller) {
        super(new DeckFactory().simpleImageDeck());
        mouseGestures = new MouseGestures(controller);
        restart = false;
        start();
    }
    private void start(){
        deck=new ArrayList<>(fullDeck);
        deckRemain=new Stack<>();
        vastPile =new Stack<>();
        placeCardsOnBoard();
    }

    @Override
    public void placeCardsOnBoard() {
        emptySpace(15, 6, board);
        emptySpace(140, 7, board);


        if (!restart){
            Collections.shuffle(deck);}


    }
    private void emptySpace(int y, int length, Pane panel) {
        for (int i = 0; i < length; i++) {
            ImageView empty = new ImageView();
            Image img = new Image("./resources/img/kuka.png");
            empty.setImage(img);
            empty.setFitWidth(70);
            empty.setLayoutX((i * empty.getFitWidth() + i * 15) + 20);
            empty.setLayoutY(y);
            empty.setPreserveRatio(true);
            empty.toBack();
            panel.getChildren().add(empty);
        }
    }

    @Override
    public void restartGame(boolean fullrestart) {
        mouseGestures.db=0;
        restart = true;
        board.getChildren().clear();
        flippCardsonDefault();
        if (fullrestart) {
            restart=false;
            deck = new ArrayList<>(fullDeck);
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
        }
    }

}
