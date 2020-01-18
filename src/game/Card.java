package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Card extends ImageView {
    private int id;
    private String name;
    private Rank rank;
    private Style color;
    private Image front, back;
    // private boolean movable;
//    private ImageView imageView;

    public Card(int id,Rank rank, Style color) {
        this.id=id;
        this.name = rank + " of " + color;
        this.rank = rank;
        this.color = color;
        //this.movable = true;
//        this.imageView=new ImageView();
    }

    //    public void  setImageView(boolean lookUp, double width, double height, double x, double y, EventHandler<MouseEvent> pressed, EventHandler<MouseEvent> dragged){
//        this.setImage(lookUp ? front : back);
//        this.setFitWidth(width);
//        this.setFitHeight(height);
//        this.setLayoutX(x);
//        this.setLayoutY(y);
//        this.setPreserveRatio(true);
//        this.setOnMousePressed(pressed);
//        this.setOnMouseDragged(dragged);
//    }
//    public ImageView getImageView() {
//        return imageView;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id &&
                Objects.equals(name, card.name) &&
                rank == card.rank &&
                color == card.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rank, color);
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
        return this.rank.getValue() > otherCard.rank.getValue();

    }

    public boolean isRankLess(Card otherCard) {
        if (this == otherCard) return false;
        if (otherCard == null || getClass() != otherCard.getClass()) return false;
        return this.rank.getValue() < otherCard.rank.getValue();
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

    public Image getFront() {
        return front;
    }

    public void setFront(Image image) {
        this.front = image;
    }

    public Image getBack() {
        return back;
    }

    public void setBack(Image back) {
        this.back = back;
    }
}
