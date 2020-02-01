package backEnd.services.factory;

import backEnd.domain.Card;
import backEnd.domain.Rank;
import backEnd.domain.Style;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {

    private final List<Card> baseDeck = simpleDeck();
    private int index = 0;

    public List<Card> simpleDeck() {
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
                Image img = new Image("./resources/img/cards/paklik/pakli4/" + rank.helper + color.helper + ".png");
                Image back = new Image("./resources/img/cards/hatlap/blue.png");
                card.setFront(img);
                card.setBack(back);
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
