package backEnd.services.game.mousegestures;

import backEnd.domain.enums.ActionType;
import backEnd.domain.Card;
import backEnd.domain.enums.Rank;
import frontEnd.MainController;
import frontEnd.settings.Alerts;
import frontEnd.settings.ScoreBoard;
import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class MouseGestureKlondike extends MouseGestures{

    private Stack<Card> deck,vastPile;
    private ImageView emptyDeck;
    List<Node> finalPozzicions;
    private ImageView empty;

    public void getDecks( Stack<Card> deck, Stack<Card> vastPile,ImageView emptyDeck,List<Node> finalPozzicions){
        this.deck=deck;
        this.vastPile=vastPile;
        this.emptyDeck=emptyDeck;
        this.finalPozzicions=finalPozzicions;
    }
    public MouseGestureKlondike(MainController mainController) {
        super(mainController);
        empty=null;
        super.onUndo= actionEvent -> {
            switch (actionType){
                case DECK:undoDeck();
                    break;
                case TOFINAL:undoToFinal();
                    break;
                case FROMDECK:undoFromDeck();
                    break;
                case SIMPLE:undoSimple();
                    break;
                default:
                    break;
            }
            actionType= ActionType.NOTHING;
        };
        super.onMousePressedEventHandler =
                t -> {
                    Card source = (Card) (t.getSource());
                    if (source.isFaceup()&&!source.isFinalPozicion() ) {
                        orgSceneX = t.getSceneX();
                        orgSceneY = t.getSceneY();

                        cardXtemp= source.getLayoutX();
                        cardYtemp= source.getLayoutY();

                        orgTranslateX = source.getTranslateX();
                        orgTranslateY = source.getTranslateY();
                        source.setMouseTransparent(true);
                        if (t.getClickCount() == 2 && !t.isConsumed()) {
                            t.consume();
                            doubleClikPozicion(finalPozzicions, source);
                            DropShadow shadow = new DropShadow();
                            shadow.setColor(Color.PURPLE);
                            source.setEffect(shadow);
                            if (db == 13 * 4) {
                                Alerts a = new Alerts();
                                a.win();
                                a.score(mainController.getScore(), mainController.getCurrentScore());
                                a.getName(mainController);
                                new ScoreBoard(new Stage(), mainController);
                                mainController.desableMenuItem();
                            }
                        }
                    }
                };
        super.onMouseDraggedEventHandler =
                t -> {
                    Card source = (Card) (t.getSource());
                    if (source.isFaceup()&&!source.isFinalPozicion()) {
                        double offsetX = t.getSceneX() - orgSceneX;
                        double offsetY = t.getSceneY() - orgSceneY;
                        double newTranslateX = orgTranslateX + offsetX;
                        double newTranslateY = orgTranslateY + offsetY;

                        recursiveTranslate(source, newTranslateX, newTranslateY);
                        source.setTranslateX(newTranslateX);
                        source.setTranslateY(newTranslateY);
                    }
                };
        super.onMouseReleasedEventHandler = event -> {
            Card card = (Card) event.getSource();
            Node picked = event.getPickResult().getIntersectedNode();
            if (card.isFaceup() && !card.isFinalPozicion()) {
                if (picked instanceof Card) {
                    Card pikedCard = (Card) picked;
                    putOnACard(card, pikedCard);
                } else if (picked instanceof ImageView && card.getRank() == Rank.KING && (card.getCardOnIt() == null || card.getCardOnIt().isSticked())) {
                    ImageView pickedPlace = (ImageView) picked;
                    putOnAnEmptyPlace(card, pickedPlace);
                } else if (picked instanceof ImageView && card.getRank() == Rank.ACE && (card.getCardOnIt() == null || card.getCardOnIt().isSticked())) {
                    ImageView pickedPlace = (ImageView) picked;
                    putOnAnEmptyPlaceAnAce(card, pickedPlace);
                } else {
                    moveToSource(card);
                    recursiveTranslateBack(card);
                }
                card.setMouseTransparent(false);
                if (db == 13 * 4) {
                    Alerts a = new Alerts();
                    a.win();
                    a.score(mainController.getScore(), mainController.getCurrentScore());
                    a.getName(mainController);
                    new ScoreBoard(new Stage(), mainController);
                    mainController.desableMenuItem();
                }
            }
            card.setEffect(null);
            card.setMouseTransparent(false);
        };
    }
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public void doubleClikPozicion(List<Node> finalpozzicion,Card card){

if (card.getRank().equals(Rank.ACE)){
    Optional<Node> optionalNode = finalpozzicion.stream()
            .filter(pickedPlace->pickedPlace.getId() != null && pickedPlace.getId().contains("row"))
            .findFirst();
    if(optionalNode.isPresent()){
        Node finalPositionCard = optionalNode.get();
        if (finalPositionCard instanceof ImageView){
        putOnAnEmptyPlaceAnAce(card,(ImageView) finalPositionCard);
        finalpozzicion.remove(finalPositionCard);
        finalpozzicion.add(card);
        }
    }

}else {
    Optional<Node> optionalCard = finalpozzicion.stream()
            .filter(o -> o instanceof Card)
            .filter(pickedCard -> !isValidSimplePlacement(card, ((Card) pickedCard)))
            .findFirst();
    if(optionalCard.isPresent()){
        Node finalPositionCard = optionalCard.get();
        if (finalPositionCard instanceof Card) {
            ifFinalPozicion(card, (Card) finalPositionCard);
            finalpozzicion.remove(finalPositionCard);
            finalpozzicion.add(card);
        }
    }
}


    }
    private void putOnAnEmptyPlace(Card card, ImageView pickedPlace) {
        if(pickedPlace.getId() != null && pickedPlace.getId().contains("col")) {
            movedCard=card;
            if (card.getCardBeforeIt() != null) {
                if (!card.getCardBeforeIt().isFaceup()){
                    card.getCardBeforeIt().flippCard();}
                sorceCard=card.getCardBeforeIt();
                card.removeConnection();
                actionType=ActionType.SIMPLE;
            }else {
                sorceCard=null;
                actionType=ActionType.FROMDECK;
            }
            targetCard=null;
            cardX=cardXtemp;
            cardY=cardYtemp;
            card.setSticked(true);
            card.setInDeck(false);
            fixPosition(card, pickedPlace,25);
        }else {
            moveToSource(card);
            recursiveTranslateBack(card);
        }
    }
    private void putOnAnEmptyPlaceAnAce(Card card, ImageView pickedPlace) {
        if(pickedPlace.getId() != null && pickedPlace.getId().contains("row")) {
            movedCard=card;
            if (card.getCardBeforeIt() != null) {
                sorceCard=card.getCardBeforeIt();
                if (!card.getCardBeforeIt().isFaceup()){
                    card.getCardBeforeIt().flippCard();}
                card.removeConnection();
               actionType=ActionType.TOFINAL;
            }else {
                sorceCard=null;
                actionType=ActionType.FROMDECK;
            }
            empty=pickedPlace;
            targetCard=null;
            cardX=cardXtemp;
            cardY=cardYtemp;
            card.setSticked(true);
            card.setFinalPozicion(true);
            card.setInDeck(false);
            fixPosition(card, pickedPlace,25);
            db++;
            int score = mainController.getCurrentScore();
            int total=mainController.getScore();
            total+=card.getPoint();
            score += card.getPoint();
            mainController.setCurrentScore(score);
            mainController.setScore(total);
            mainController.setScoreText(mainController.getOwnMenu().setTFScore(mainController.getScoreText(),mainController.getScore(), mainController.getCurrentScore()));
        }else {
            moveToSource(card);
            recursiveTranslateBack(card);
        }
    }

    private void putOnACard(Card card, Card pikedCard) {
        if (isValidSimplePlacement(card, pikedCard)) {
            onAnyCardInGame(card, pikedCard);
        } else {
            ifFinalPozicion(card, pikedCard);
        }
    }
    private boolean isValidSimplePlacement(Card card, Card pikedCard) {
        return !pikedCard.isColorEqual(card) || !pikedCard.isRankLess(card) || card.getCardOnIt() != null || !pikedCard.isFinalPozicion();
    }
    private void onAnyCardInGame(Card card, Card pikedCard) {
        if(isValidPlacement(card, pikedCard)){
            if (!pikedCard.isInDeck()) {
                movedCard=card;
                if (card.getCardBeforeIt() != null) {
                    if (!card.getCardBeforeIt().isFaceup()){
                        card.getCardBeforeIt().flippCard();}
                    sorceCard=card.getCardBeforeIt();
                    card.removeConnection();
                    actionType= ActionType.SIMPLE;
                }else if (card.getCardOnIt()!=null){
                    sorceCard=null;
                    actionType=ActionType.SIMPLE;
                }else {
                    sorceCard=null;
                    actionType=ActionType.FROMDECK;
                }
                targetCard=pikedCard;
                pikedCard.setConnection(card);
                card.setSticked(true);
                card.setInDeck(false);
                cardX=cardXtemp;
                cardY=cardYtemp;
                fixPosition(card, pikedCard,25);
            } else {
                moveToSource(card);
                recursiveTranslateBack(card);
            }
        }else {
            moveToSource(card);
            recursiveTranslateBack(card);
        }
    }

    private void ifFinalPozicion(Card card, Card pikedCard) {
        movedCard=card;
        if (card.getCardBeforeIt() != null) {
            if (!card.getCardBeforeIt().isFaceup()){
                card.getCardBeforeIt().flippCard();}
            sorceCard=card.getCardBeforeIt();
            card.removeConnection();
            actionType=ActionType.TOFINAL;
        }else {
            sorceCard=null;
            actionType=ActionType.FROMDECK;
        }
        targetCard=pikedCard;
        cardX=cardXtemp;
        cardY=cardYtemp;
        pikedCard.setConnection(card);
        card.setInDeck(false);
        card.setFinalPozicion(true);
        db++;
        finalPosition(card, pikedCard);
        finalPozzicions.remove(pikedCard);
        finalPozzicions.add(card);
        int score = mainController.getCurrentScore();
        int total=mainController.getScore();
        total+=card.getPoint();
        score += card.getPoint();
        mainController.setCurrentScore(score);
        mainController.setScore(total);
        mainController.setScoreText(mainController.getOwnMenu().setTFScore(mainController.getScoreText(),mainController.getScore(), mainController.getCurrentScore()));
    }

    private boolean isValidPlacement(Card card, Card pikedCard) {
        return !card.isSticked()
                &&((!pikedCard.isBlack() && card.isBlack()) || (pikedCard.isBlack() && !card.isBlack()))
                &&  pikedCard.isRankGreater(card)
                && (card.getCardOnIt() == null || card.getCardOnIt().isSticked())
                &&(pikedCard.getCardOnIt()==null||pikedCard.getCardOnIt().equals(card));
    }

    public static void recursiveTranslate(Card source, double newTranslateX, double newTranslateY) {
        Card cardOnIt = source.getCardOnIt();
        source.toFront();
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        source.setEffect(shadow);
        if (cardOnIt != null) {
            cardOnIt.setTranslateX(newTranslateX);
            cardOnIt.setTranslateY(newTranslateY);
            recursiveTranslate(cardOnIt, newTranslateX, newTranslateY);
        }
    }

    public static void recursiveTranslateBack(Card source) {
        Card cardOnIt = source.getCardOnIt();
        source.toFront();
        source.setEffect(null);
        if (cardOnIt != null) {
            double sourceX = cardOnIt.getLayoutX() + cardOnIt.getTranslateX();
            double sourceY = cardOnIt.getLayoutY() + cardOnIt.getTranslateY();

            double targetX = cardOnIt.getLayoutX();
            double targetY = cardOnIt.getLayoutY();

            if (!(sourceX == targetX && sourceY == targetY)) {
                Path path = new Path();
                path.getElements().add(new MoveToAbs(cardOnIt, sourceX, sourceY));
                path.getElements().add(new LineToAbs(cardOnIt, targetX, targetY));

                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(100));
                pathTransition.setNode(cardOnIt);
                pathTransition.setPath(path);
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(true);

                pathTransition.play();

            }
            recursiveTranslateBack(cardOnIt);
        }
    }


    private void fixPosition(Card card, Node cardTo,int hight) {


        card.toFront();
        card.setEffect(null);
        double xto = cardTo.getLayoutX();
        double yto = cardTo.getLayoutY();


        if (card.getRank().equals(Rank.KING)||card.getRank().equals(Rank.ACE)&&!(cardTo instanceof Card)) {
            card.relocate(xto, yto-5);
        } else {
            card.relocate(xto, yto + hight);
        }

        card.setTranslateX(0);
        card.setTranslateY(0);
        Card cardOnIt = card.getCardOnIt();
        if (cardOnIt != null) {
            xto = card.getLayoutX();
            yto = card.getLayoutY();

            cardOnIt.relocate(xto, yto + 25);

            cardOnIt.setTranslateX(0);
            cardOnIt.setTranslateY(0);
            fixPosition(cardOnIt, card,25);
        }


    }

    private void finalPosition(Card card, Node cardTo) {
        card.toFront();
        card.setEffect(null);
        double xto = cardTo.getLayoutX();
        double yto = cardTo.getLayoutY();
        card.relocate(xto, yto);
        card.setTranslateX(0);
        card.setTranslateY(0);
    }

    private void moveToSource(Card card) {
        double sourceX = card.getLayoutX() + card.getTranslateX();
        double sourceY = card.getLayoutY() + card.getTranslateY();

        double targetX = card.getLayoutX();
        double targetY = card.getLayoutY();

        if (!(sourceX == targetX && sourceY == targetY)) {
            Path path = new Path();
            path.getElements().add(new MoveToAbs(card, sourceX, sourceY));
            path.getElements().add(new LineToAbs(card, targetX, targetY));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(100));
            pathTransition.setNode(card);
            pathTransition.setPath(path);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);

            pathTransition.play();
        }
    }
    public void undoSimple(){
        if (movedCard!=null&&sorceCard!=null&&targetCard!=null){
            movedCard.removeConnection();
            sorceCard.setConnection(movedCard);
            if (movedCard.getCardBeforeIt().isFaceup()){
                movedCard.getCardBeforeIt().flippCard();}
            movedCard.setSticked(false);
            fixPosition(movedCard,sorceCard,10);
        }else if(movedCard!=null&&sorceCard!=null){
            sorceCard.setConnection(movedCard);
            if (movedCard.getCardBeforeIt().isFaceup()){
                movedCard.getCardBeforeIt().flippCard();}
            fixPosition(movedCard,sorceCard,10);
        }else if(movedCard!=null&&targetCard!=null){
            movedCard.removeConnection();
            Node to=movedCard;
            to.setLayoutX(cardX);
            to.setLayoutY(cardY-10);
            fixPosition(movedCard,to,10);
        }
    }
    public void undoFromDeck(){
        if (movedCard!=null&&targetCard!=null){
            if (targetCard.isFinalPozicion()){
                movedCard.setFinalPozicion(false);
                int score = mainController.getCurrentScore();
                int total = mainController.getScore();
                total -= movedCard.getPoint();
                score -= movedCard.getPoint();
                mainController.setCurrentScore(score);
                mainController.setScore(total);
                mainController.setScoreText(mainController.getOwnMenu().setTFScore(mainController.getScoreText(), mainController.getScore(), mainController.getCurrentScore()));
                db--;
                finalPozzicions.remove(movedCard);
                finalPozzicions.add(targetCard);
            }else {

                movedCard.setSticked(false);
            }
            movedCard.removeConnection();
            movedCard.setInDeck(true);
            movedCard.relocate(cardX,cardY);
        }else if (movedCard!=null&&movedCard.isFinalPozicion()){
            movedCard.setFinalPozicion(false);
            int score = mainController.getCurrentScore();
            int total = mainController.getScore();
            total -= movedCard.getPoint();
            score -= movedCard.getPoint();
            mainController.setCurrentScore(score);
            mainController.setScore(total);
            mainController.setScoreText(mainController.getOwnMenu().setTFScore(mainController.getScoreText(), mainController.getScore(), mainController.getCurrentScore()));
            db--;
            movedCard.setSticked(false);
            finalPozzicions.remove(movedCard);
            finalPozzicions.add(empty);
            movedCard.setInDeck(true);
            movedCard.relocate(cardX,cardY);
        }else if (movedCard!=null&&movedCard.getRank().equals(Rank.KING)){


                movedCard.setSticked(false);
            movedCard.setInDeck(true);
            movedCard.relocate(cardX,cardY);
        }

    }
    public void undoDeck(){
        if (!vastPile.empty()) {

            for (int i = 0; i < 1; i++) {
                int pozicion=deck.size();
                Card actual = vastPile.pop();
                actual.flippCard();
                actual.relocate(((pozicion * 0.002) * actual.getFitWidth()) + 30, 10);
                actual.toFront();
                actual.setInDeck(true);
                deck.push(actual);
                emptyDeck.toFront();
            }
        } else {

            while (deck.size() != 0) {
                Card actual = deck.pop();
                if (!actual.isSticked() && actual.isInDeck()) {
                    actual.flippCard();
                    actual.relocate(120, 10);
                    vastPile.push(actual);
                } else {
                    actual.setInDeck(false);
                }
            }

        }

    }
    public void undoToFinal(){
        if (movedCard!=null&&sorceCard!=null&&targetCard!=null){
            sorceCard.setConnection(movedCard);
            movedCard.setFinalPozicion(false);
            if (movedCard.getCardBeforeIt().isFaceup()&&!sorceCard.isSticked()){
                movedCard.getCardBeforeIt().flippCard();
                fixPosition(movedCard,sorceCard,10);
            }
            fixPosition(movedCard,sorceCard,25);
            int score = mainController.getCurrentScore();
            int total = mainController.getScore();
            total -= movedCard.getPoint();
            score -= movedCard.getPoint();
            mainController.setCurrentScore(score);
            mainController.setScore(total);
            mainController.setScoreText(mainController.getOwnMenu().setTFScore(mainController.getScoreText(), mainController.getScore(), mainController.getCurrentScore()));
            db--;
            finalPozzicions.remove(movedCard);
            finalPozzicions.add(targetCard);
        }else if (movedCard!=null&&sorceCard!=null){
            sorceCard.setConnection(movedCard);
            movedCard.setFinalPozicion(false);
            if (movedCard.getCardBeforeIt().isFaceup()&&!sorceCard.isSticked()){
                movedCard.getCardBeforeIt().flippCard();
                fixPosition(movedCard,sorceCard,10);
            }
            fixPosition(movedCard,sorceCard,25);
            int score = mainController.getCurrentScore();
            int total = mainController.getScore();
            total -= movedCard.getPoint();
            score -= movedCard.getPoint();
            mainController.setCurrentScore(score);
            mainController.setScore(total);
            mainController.setScoreText(mainController.getOwnMenu().setTFScore(mainController.getScoreText(), mainController.getScore(), mainController.getCurrentScore()));
            db--;
            finalPozzicions.remove(movedCard);
            finalPozzicions.add(empty);
        }
    }
    public static class MoveToAbs extends MoveTo {

        public MoveToAbs(Card card, double x, double y) {
            super(x - card.getLayoutX() + card.getLayoutBounds().getWidth() / 2, y - card.getLayoutY() + card.getLayoutBounds().getHeight() / 2);
        }

    }

    public static class LineToAbs extends LineTo {

        public LineToAbs(Card card, double x, double y) {
            super(x - card.getLayoutX() + card.getLayoutBounds().getWidth() / 2, y - card.getLayoutY() + card.getLayoutBounds().getHeight() / 2);
        }

    }
}
