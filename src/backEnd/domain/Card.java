package backEnd.domain;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Card extends ImageView {
    private int id;
    private int point;
    private String name;
    private Rank rank;
    private Style color;
    private Image front, back;
    private Card cardOnIt;
    private Card cardBeforeIt;
    private boolean faceup;
    private boolean inDeck;
    private boolean isSticked;
    private boolean finalPozicion;



    protected Card() {}

    public Card(int id, Rank rank, Style color) {
        this.id = id;
        this.point=rank.getValue()*color.getValue();
        this.name = rank + " of " + color;
        this.rank = rank;
        this.color = color;
        this.faceup = false;
        this.isSticked =false;
        this.inDeck=false;
        this.isSticked =false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id &&
                point == card.point &&
                faceup == card.faceup &&
                inDeck == card.inDeck &&
                isSticked == card.isSticked &&
                name.equals(card.name) &&
                rank == card.rank &&
                color == card.color &&
                front.equals(card.front) &&
                back.equals(card.back) &&
                Objects.equals(cardOnIt, card.cardOnIt) &&
                Objects.equals(cardBeforeIt, card.cardBeforeIt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, point, name, rank, color, front, back, cardOnIt, cardBeforeIt, faceup, inDeck, isSticked);
    }

    public boolean RankEqual(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return this.rank.equals(card.rank);
    }

    public boolean isRankGreater(Card otherCard) {
        if (this == otherCard) return false;
        if (otherCard == null || getClass() != otherCard.getClass()) return false;
        return this.rank.getValue() == otherCard.rank.getValue() + 1;

    }

    public boolean isRankLess(Card otherCard) {
        if (this == otherCard) return false;
        if (otherCard == null || getClass() != otherCard.getClass()) return false;
        return this.rank.getValue() + 1 == otherCard.rank.getValue();
    }

    public boolean isColorEqual(Card otherCard) {
        if (this == otherCard) return true;
        if (otherCard == null || getClass() != otherCard.getClass()) return false;
        return this.color.equals(otherCard.color);
    }

    public boolean isBlack() {
        return this.color.equals(Style.CLUBS) || this.color.equals(Style.SPADES);
    }


    public Rank getRank() {
        return rank;
    }

    public Style getColor() {
        return color;
    }

    public Image getFront() {
        return front;
    }

    public void setFront(Image image) {
        this.front = image;
    }

    public void flippCard() {
        faceup = !faceup;
        if (faceup) {
            this.setImage(front);
        } else {
            this.setImage(back);
        }
    }

    public boolean isFaceup() {
        return faceup;
    }

    public Image getBack() {
        return back;
    }

    public void setBack(Image back) {
        this.back = back;
    }

    public Card getCardOnIt() {
        return cardOnIt;
    }

    public void setCardOnIt(Card cardOnIt) {
        this.cardOnIt = cardOnIt;
    }
    public void setConnection(Card cardOnIt){
        this.cardOnIt = cardOnIt;
        cardOnIt.setCardBeforeIt(this);
    }
    public void removeConnection(){
        this.getCardBeforeIt().setCardOnIt(null);
        this.setCardBeforeIt(null);
    }

    public Card getCardBeforeIt() {
        return cardBeforeIt;
    }

    public void setCardBeforeIt(Card cardBeforeIt) {
        this.cardBeforeIt = cardBeforeIt;
    }

    public boolean isSticked() {
        return isSticked;
    }

    public void setSticked(boolean sticked) {
        this.isSticked = sticked;
    }

    public boolean isInDeck() {
        return inDeck;
    }

    public void setInDeck(boolean inDeck) {
        this.inDeck = inDeck;
    }

    public int getPoint() {
        return point;
    }

    public boolean isFinalPozicion() {
        return finalPozicion;
    }

    public void setFinalPozicion(boolean finalPozicion) {
        this.finalPozicion = finalPozicion;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                '}';
    }
}
