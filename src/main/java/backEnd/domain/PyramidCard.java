package backEnd.domain;

import backEnd.domain.enums.Rank;
import backEnd.domain.enums.Style;

public class PyramidCard extends Card {
    private PyramidCard cardOnIt2;
    private PyramidCard CardBeforeIt2;
    private boolean vaistpile;

    public PyramidCard getCardBeforeIt2() {
        return CardBeforeIt2;
    }

    public void setCardBeforeIt2(PyramidCard cardBeforeIt2) {
        CardBeforeIt2 = cardBeforeIt2;
    }

    public PyramidCard(int id, Rank rank, Style color) {
        super(id, rank, color);
        this.vaistpile=false;
    }

    public PyramidCard getCardOnIt2() {
        return cardOnIt2;
    }

    public void setCardOnIt2(PyramidCard cardOnIt2) {
        this.cardOnIt2 = cardOnIt2;
    }
    public void setConnection2(PyramidCard cardOnIt2){
        this.cardOnIt2 = cardOnIt2;
        cardOnIt2.setCardBeforeIt2(this);
    }
    public void removeConnection2(){
        this.getCardBeforeIt2().setCardOnIt2(null);
        this.setCardBeforeIt2(null);
    }

    public boolean isVaistpile() {
        return vaistpile;
    }

    public void setVaistpile(boolean vaistpile) {
        this.vaistpile = vaistpile;
    }
}
