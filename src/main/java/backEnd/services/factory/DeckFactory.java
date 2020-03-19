package backEnd.services.factory;

import backEnd.domain.Card;
import backEnd.domain.Rank;
import backEnd.domain.Style;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {

    public static final String DECK = "img/cards/paklik/";
    public static final String BACKOFCARD = "img/cards/hatlap/";
    private final List<Card> baseDeck = simpleImageDeck();
    private int index = 0;

    private List<Card> simpleDeck() {
        int index = 0;
        List<Card> deck = new ArrayList<>();
        for (Style color : Style.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(index, rank, color));
                index++;
            }
        }
        return deck;
    }

    public List<Card> simpleImageDeck() {
        Asset.Asset();
        List<Card> deck = new ArrayList<>();
        for (Style color : Style.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(this.index, rank, color);
                card.setFront(Asset.getAssets().getOrDefault(rank.helper + "" + color.helper, new Image(DECK + Config.properties.getProperty("front") + "/" + rank.helper + color.helper + ".png")));
                card.setBack(Asset.getAssets().getOrDefault("back", new Image(BACKOFCARD + Config.properties.getProperty("back") + ".png")));
                card.setFitHeight(100);
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
