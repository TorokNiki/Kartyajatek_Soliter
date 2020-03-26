package backEnd.services.game.mousegestures;

import backEnd.domain.Card;
import backEnd.domain.PyramidCard;
import backEnd.domain.enums.ActionType;
import backEnd.domain.enums.Rank;
import frontEnd.MainController;
import frontEnd.settings.Alerts;
import frontEnd.settings.ScoreBoard;
import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Stack;

public class MouseGesturesPyramid extends MouseGestures {
    ImageView finalPozzicion;
    private Stack<PyramidCard> deck, vastPile;
    private ImageView emptyDeck;

    public MouseGesturesPyramid(MainController mainController) {
        super(mainController);
        super.onUndo = actionEvent -> {
            switch (actionType) {
                case DECK:
                    undoDeck();
                    break;
                case TOFINAL:
                    undoToFinal();
                    break;
                case FROMDECK:
                    undoFromDeck();
                    break;
                case SIMPLE:
                    undoSimple();
                    break;
                default:
                    break;
            }
            actionType = ActionType.NOTHING;
        };
        super.onMousePressedEventHandler =
                t -> {
                    PyramidCard source = (PyramidCard) (t.getSource());
                    if (!source.isFinalPozicion() && source.isFaceup() && source.getCardOnIt() == null && source.getCardOnIt2() == null) {
                        orgSceneX = t.getSceneX();
                        orgSceneY = t.getSceneY();

                        cardXtemp = source.getLayoutX();
                        cardYtemp = source.getLayoutY();

                        orgTranslateX = source.getTranslateX();
                        orgTranslateY = source.getTranslateY();
                        source.setMouseTransparent(true);
                        source.toFront();
                        if (t.getClickCount() == 2 && !t.isConsumed()) {
                            t.consume();
                            doubleClikPozicion(source);

                        }
                    }
                };
        super.onMouseDraggedEventHandler =
                t -> {
                    PyramidCard source = (PyramidCard) (t.getSource());
                    if (!source.isFinalPozicion() && source.isFaceup() && source.getCardOnIt() == null && source.getCardOnIt2() == null) {
                        double offsetX = t.getSceneX() - orgSceneX;
                        double offsetY = t.getSceneY() - orgSceneY;
                        double newTranslateX = orgTranslateX + offsetX;
                        double newTranslateY = orgTranslateY + offsetY;

                        source.setTranslateX(newTranslateX);
                        source.setTranslateY(newTranslateY);
                    }
                };
        super.onMouseReleasedEventHandler = event -> {
            PyramidCard card = (PyramidCard) event.getSource();
            Node picked = event.getPickResult().getIntersectedNode();
            if (!card.isFinalPozicion() && card.isFaceup() && card.getCardOnIt() == null && card.getCardOnIt2() == null) {
                if (picked instanceof Card) {
                    PyramidCard pikedCard = (PyramidCard) picked;
                    putOnACard(card, pikedCard);
                } else if (picked instanceof ImageView && card.getRank() == Rank.KING && (card.getCardOnIt() == null || card.getCardOnIt().isSticked())) {
                    ImageView pickedPlace = (ImageView) picked;
                    placeAKing(card,pickedPlace);
                } else if (picked instanceof Node) {
                    Node pickedPlace =  picked;
                    putOnAnEmptyPlace(card,pickedPlace);
                }else {
                    moveToSource(card);
                }
                card.setMouseTransparent(false);
                if (db == 13 * 4) {
                    Alerts a = new Alerts();
                    a.win();
                    a.score(mainController.getScore(), mainController.getCurrentScore());
                    a.getName(mainController);
                    new ScoreBoard(new Stage());
                    mainController.desableMenuItem();
                }

            }
            card.setEffect(null);
            card.setMouseTransparent(false);
        };
    }
    public void placeAKing(PyramidCard card,Node pickedPlace){
        if (pickedPlace.getId() != null && pickedPlace.getId().contains("row")){
            putOnFinalPlace(card, pickedPlace);
        }else {
            putOnAnEmptyPlace(card,pickedPlace);
        }
    }

    public void getDecks(Stack<PyramidCard> deck, Stack<PyramidCard> vastPile, ImageView emptyDeck, ImageView finalPozzicion) {
        this.deck = deck;
        this.vastPile = vastPile;
        this.emptyDeck = emptyDeck;
        this.finalPozzicion = finalPozzicion;
    }

    public void doubleClikPozicion(PyramidCard card) {

        if (card.getRank().equals(Rank.KING)) {
            putOnFinalPlace(card, finalPozzicion);
            card.toFront();
            DropShadow shadow = new DropShadow();
            shadow.setColor(Color.PURPLE);
            card.setEffect(shadow);
        }

    }

    private void putOnFinalPlace(PyramidCard card, Node pickedPlace) {
        if (pickedPlace.getId() != null && pickedPlace.getId().contains("row")) {
            movedCard = card;
            if (card.getCardBeforeIt() != null) {
                sorceCard = card.getCardBeforeIt();
                card.removeConnection();
                actionType = ActionType.TOFINAL;
            } else {
                sorceCard = null;
                actionType = ActionType.FROMDECK;
                if (!card.isVaistpile()&&card.isInDeck()){
                showDeck();
                }
            }
            if (card.getCardBeforeIt2() != null) {
                card.removeConnection2();
            }
            targetCard = null;
            cardX = cardXtemp;
            cardY = cardYtemp;
            card.setSticked(true);
            card.setFinalPozicion(true);
            card.setInDeck(false);
            fixPosition(card, pickedPlace);
            addScore(card);
        }
        else {
            moveToSource(card);
        }
    }
    protected void showDeck() {
        setActionType(ActionType.DECK);
        if (!deck.empty()) {
            int db = Math.min(deck.size(), 1);
            for (int i = 0; i < db; i++) {
                PyramidCard actual = deck.pop();
                actual.flippCard();
                setMouseGestures(actual);
                actual.toFront();
                actual.setInDeck(true);
                vastPile.push(actual);
            }
        }

    }
    private void putOnAnEmptyPlace(PyramidCard card, Node pickedPlace) {
        if (pickedPlace.getId() != null && pickedPlace.getId().contains("col")) {
            movedCard=card;
            sorceCard=null;
            actionType=ActionType.FROMDECK;
            targetCard=null;
            cardX=cardXtemp;
            cardY=cardYtemp;
            fixPosition(card, pickedPlace);
            showDeck();
            card.setInDeck(true);
           card.setVaistpile(true);
        }
        else {
            moveToSource(card);
        }
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    private void putOnACard(PyramidCard card, PyramidCard pikedCard) {
        if (isValidSimplePlacement(card, pikedCard)) {
            ifFinalPozicion(card,pikedCard);
        }else if ( pikedCard.isVaistpile()&&pikedCard.isInDeck()){
            movedCard=card;
            sorceCard=null;
            actionType=ActionType.FROMDECK;
            targetCard=null;
            cardX=cardXtemp;
            cardY=cardYtemp;
            fixPosition(card, pikedCard);
            if (!card.isVaistpile()&&card.isInDeck()) {
                showDeck();
            }
            card.setVaistpile(true);
            card.setInDeck(true);
        }
        else {
            moveToSource(card);
        }
    }

    private boolean isValidSimplePlacement(Card card, Card pikedCard) {
        return card.getRank().getValue()+pikedCard.getRank().getValue()==13;
    }

    private void ifFinalPozicion(PyramidCard card,PyramidCard target) {
        movedCard = card;
        if (card.getCardBeforeIt() != null) {
            sorceCard = card.getCardBeforeIt();
            card.removeConnection();
            actionType = ActionType.TOFINAL;
            if (!target.isVaistpile()&&target.isInDeck()) {
                showDeck();
            }
        } else {
            sorceCard = null;
            actionType = ActionType.FROMDECK;
            if (!card.isVaistpile()&&card.isInDeck()) {
                showDeck();
            }else if (card.isVaistpile()&&card.isInDeck()&&target.isInDeck()&&!target.isVaistpile()){
                showDeck();
            }
        }
        if (target.getCardBeforeIt() != null){
            target.removeConnection();
        }
        if (card.getCardBeforeIt2() != null) {
        card.removeConnection2();
        }
        if (target.getCardBeforeIt2() != null) {
            target.removeConnection2();
        }
        targetCard = null;
        cardX = cardXtemp;
        cardY = cardYtemp;
        card.setSticked(true);
        card.setFinalPozicion(true);
        target.setFinalPozicion(true);
        card.setInDeck(false);
        fixPosition(card, finalPozzicion);
        fixPosition(target, finalPozzicion);
        addScore(card,target);
    }

    private void addScore(Card card) {
        db++;
        int score = mainController.getCurrentScore();
        int total = mainController.getScore();
        total += card.getPoint();
        score += card.getPoint();
        mainController.setCurrentScore(score);
        mainController.setScore(total);
        mainController.setScoreText(mainController.getOwnMenu().setTFScore(mainController.getScoreText(), mainController.getScore(), mainController.getCurrentScore()));
    }
    private void addScore(Card card,Card card2) {
        db++;
        int score = mainController.getCurrentScore();
        int total = mainController.getScore();
        total += card.getPoint()+card2.getPoint();
        score += card.getPoint()+card2.getPoint();
        mainController.setCurrentScore(score);
        mainController.setScore(total);
        mainController.setScoreText(mainController.getOwnMenu().setTFScore(mainController.getScoreText(), mainController.getScore(), mainController.getCurrentScore()));
    }
    private void fixPosition(Card card, Node cardTo) {
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
            path.getElements().add(new MouseGesturesUpsideDownPiramid.MoveToAbs(card, sourceX, sourceY));
            path.getElements().add(new MouseGesturesUpsideDownPiramid.LineToAbs(card, targetX, targetY));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(100));
            pathTransition.setNode(card);
            pathTransition.setPath(path);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);

            pathTransition.play();
        }
    }

    public void undoSimple() {
        if (movedCard != null && sorceCard != null && targetCard != null) {
            movedCard.removeConnection();
            sorceCard.setConnection(movedCard);
            movedCard.setSticked(false);
            fixPosition(movedCard, sorceCard);
        } else if (movedCard != null && sorceCard != null) {
            sorceCard.setConnection(movedCard);
            fixPosition(movedCard, sorceCard);
        } else if (movedCard != null && targetCard != null) {
            movedCard.removeConnection();
            Node to = movedCard;
            to.setLayoutX(cardX);
            to.setLayoutY(cardY - 25);
            fixPosition(movedCard, to);
        }
    }

    public void undoFromDeck() {
        if (movedCard != null && targetCard != null) {
            if (targetCard.isFinalPozicion()) {
                undoScore();
                movedCard.setFinalPozicion(false);
            } else {
                movedCard.setSticked(false);
            }
            movedCard.removeConnection();
            movedCard.setInDeck(true);
            movedCard.relocate(cardX, cardY);
        } else if (movedCard != null && movedCard.getRank().equals(Rank.KING)) {
            movedCard.setSticked(false);
            movedCard.setInDeck(true);
            movedCard.setFinalPozicion(false);
            movedCard.relocate(cardX, cardY);
            undoScore();
        }

    }

    public void undoDeck() {
        if (!vastPile.empty()) {
            int db = Math.min(vastPile.size(), 1);
            for (int i = 0; i < db; i++) {
                int pozicion = deck.size();
                PyramidCard actual = vastPile.pop();
                actual.flippCard();
                actual.relocate(((pozicion * 0.002) * actual.getFitWidth()) + 30, 50);
                actual.toFront();
                actual.setInDeck(true);
                actual.setVaistpile(false);
                deck.push(actual);
                emptyDeck.toFront();
            }
        }
    }

    public void undoToFinal() {
        if (movedCard != null && sorceCard != null && targetCard != null) {
            sorceCard.setConnection(movedCard);
            fixPosition(movedCard, sorceCard);
            undoScore();
            movedCard.setFinalPozicion(false);
        } else if (movedCard != null && targetCard != null) {
            Node to = movedCard;
            to.setLayoutX(cardX);
            to.setLayoutY(cardY);
            fixPosition(movedCard, to);
            undoScore();
            movedCard.setFinalPozicion(false);
        } else if (movedCard != null && movedCard.getRank().equals(Rank.KING)) {
            movedCard.setFinalPozicion(false);
            Node to = movedCard;
            to.setLayoutX(cardX);
            to.setLayoutY(cardY - 25);
            fixPosition(movedCard, to);
            undoScore();
        }
    }

    private void undoScore() {
        int score = mainController.getCurrentScore();
        int total = mainController.getScore();
        total -= movedCard.getPoint();
        score -= movedCard.getPoint();
        mainController.setCurrentScore(score);
        mainController.setScore(total);
        mainController.setScoreText(mainController.getOwnMenu().setTFScore(mainController.getScoreText(), mainController.getScore(), mainController.getCurrentScore()));
        db--;
    }
}
