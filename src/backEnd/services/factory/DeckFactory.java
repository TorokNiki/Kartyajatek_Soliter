package backEnd.services.factory;

import backEnd.domain.Card;
import backEnd.domain.Rank;
import backEnd.domain.Style;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DeckFactory {

    public static final String DECK = "./resources/img/cards/paklik/";
    public static final String BACKOFCARD = "./resources/img/cards/hatlap/";
    private final List<Card> baseDeck = simpleDeck();
    private int index = 0;
    private Config config=new Config();



    private List<Card> simpleDeck() {
        int index = 0;
        List<Card> deck = new ArrayList<>();
        for (var color : Style.values()) {
            for (var rank : Rank.values()) {
                deck.add(new Card(index, rank, color));
                index++;
            }
        }
        return deck;
    }

    public List<Card> simpleImageDeck() {
        List<Card> deck = new ArrayList<>();
        for (var color : Style.values()) {
            for (var rank : Rank.values()) {
                Card card = new Card(this.index, rank, color);
                Image img = new Image(DECK +config.properties.getProperty("front")+"/" + rank.helper + color.helper + ".png");
                Image back = new Image(BACKOFCARD +config.properties.getProperty("back")+".png");
                card.setFront(img);
                card.setBack(back);
                card.setFitHeight(110);
                card.setFitWidth(70);
                deck.add(card);
                this.index++;
            }
        }
        return deck;
    }

    public List<Card> doubleDeck() {
        List<Card> deck = new ArrayList<>(simpleImageDeck());
        deck.addAll(simpleImageDeck());
        return deck;
    }
}
