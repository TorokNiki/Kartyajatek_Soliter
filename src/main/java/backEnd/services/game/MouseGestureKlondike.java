package backEnd.services.game;

import backEnd.domain.Card;
import backEnd.domain.Rank;
import frontEnd.MainController;
import frontEnd.settings.Alerts;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class MouseGestureKlondike {
    public double orgTranslateX, orgTranslateY;
    public double orgSceneX, orgSceneY;
    public int db=0;
    public MainController mainController;

    public MouseGestureKlondike(MainController mainController) {
        this.mainController = mainController;
    }

    EventHandler<MouseEvent> onMouseClickEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getClickCount() == 2 && !mouseEvent.isConsumed()) {
                        mouseEvent.consume();
                        Card card = (Card) mouseEvent.getSource();
                        DropShadow shadow = new DropShadow();
                        shadow.setColor(Color.PURPLE);
                        card.setEffect(shadow);

                    }
                }
            };
    EventHandler<MouseEvent> onMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Card source = (Card) (t.getSource());
                    if (source.isFaceup()&&!source.isFinalPozicion() ) {
                        orgSceneX = t.getSceneX();
                        orgSceneY = t.getSceneY();

                        orgTranslateX = source.getTranslateX();
                        orgTranslateY = source.getTranslateY();
                        source.setMouseTransparent(true);
                    }
                }
            };
    EventHandler<MouseEvent> onMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
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
                }
            };
    EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            Card card = (Card) event.getSource();
            Node picked = event.getPickResult().getIntersectedNode();
            if(picked instanceof Card){
                Card pikedCard = (Card) picked;
                putOnACard(card, pikedCard);
            }else if (picked instanceof ImageView && card.getRank() == Rank.KING && (card.getCardOnIt() == null || card.getCardOnIt().isSticked())){
                ImageView pickedPlace = (ImageView) picked;
                putOnAnEmptyPlace(card, pickedPlace);
            }else if (picked instanceof ImageView && card.getRank() == Rank.ACE && (card.getCardOnIt() == null || card.getCardOnIt().isSticked())){
                ImageView pickedPlace = (ImageView) picked;
                putOnAnEmptyPlaceAnAce(card, pickedPlace);
            }else {
                moveToSource(card);
                recursiveTranslateBack(card);
            }
            card.setMouseTransparent(false);
            if (db==13*4){
                Alerts a=new Alerts();
                a.win();
                a.score(mainController.getScore(),mainController.getCurrentScore());
            }
        }
    };

    private void putOnAnEmptyPlace(Card card, ImageView pickedPlace) {
        if(pickedPlace.getId() != null && pickedPlace.getId().contains("col")) {
            if (card.getCardBeforeIt() != null) {
                if (!card.getCardBeforeIt().isFaceup()){
                    card.getCardBeforeIt().flippCard();}
                card.removeConnection();
            }
            card.setSticked(true);
            card.setInDeck(false);
            fixPosition(card, pickedPlace);
        }else {
            moveToSource(card);
            recursiveTranslateBack(card);
        }
    }
    private void putOnAnEmptyPlaceAnAce(Card card, ImageView pickedPlace) {
        if(pickedPlace.getId() != null && pickedPlace.getId().contains("row")) {
            if (card.getCardBeforeIt() != null) {
                if (!card.getCardBeforeIt().isFaceup()){
                    card.getCardBeforeIt().flippCard();}
                card.removeConnection();
            }
            card.setSticked(true);
            card.setFinalPozicion(true);
            card.setInDeck(false);
            fixPosition(card, pickedPlace);
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
        if (!pikedCard.isColorEqual(card) || !pikedCard.isRankLess(card) || card.getCardOnIt() != null||!pikedCard.isFinalPozicion()) {
            onAnyCardInGame(card, pikedCard);
        } else {
            ifFinalPozicion(card, pikedCard);
        }
    }

    private void onAnyCardInGame(Card card, Card pikedCard) {
        if(isValidPlacement(card, pikedCard)){
            if (!pikedCard.isInDeck()) {
                if (card.getCardBeforeIt() != null) {
                    if (!card.getCardBeforeIt().isFaceup()){
                        card.getCardBeforeIt().flippCard();}
                    card.removeConnection();
                }
                pikedCard.setConnection(card);
                card.setSticked(true);
                card.setInDeck(false);
                fixPosition(card, pikedCard);
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
        if (card.getCardBeforeIt() != null) {
            if (!card.getCardBeforeIt().isFaceup()){
                card.getCardBeforeIt().flippCard();}
            card.removeConnection();
        }
        pikedCard.setConnection(card);
        card.setSticked(true);
        card.setInDeck(false);
        card.setFinalPozicion(true);
        db++;
        finalPosition(card, pikedCard);
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
            //cardOnIt.setEffect(null);
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
                path.getElements().add(new MouseGestures.MoveToAbs(cardOnIt, sourceX, sourceY));
                path.getElements().add(new MouseGestures.LineToAbs(cardOnIt, targetX, targetY));

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


    public void MouseGestures(final Card card) {
        card.setOnMouseClicked(onMouseClickEventHandler);
        card.setOnMousePressed(onMousePressedEventHandler);
        card.setOnMouseDragged(onMouseDraggedEventHandler);
        card.setOnMouseReleased(onMouseReleasedEventHandler);
    }

    private void fixPosition(Card card, Node cardTo) {


        card.toFront();
        card.setEffect(null);
        double xto = cardTo.getLayoutX();
        double yto = cardTo.getLayoutY();


        if (card.getRank().equals(Rank.KING)||card.getRank().equals(Rank.ACE)) {
            card.relocate(xto, yto-5);
        } else {
            card.relocate(xto, yto + 25);
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
            fixPosition(cardOnIt, card);
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
            path.getElements().add(new MouseGestures.MoveToAbs(card, sourceX, sourceY));
            path.getElements().add(new MouseGestures.LineToAbs(card, targetX, targetY));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(100));
            pathTransition.setNode(card);
            pathTransition.setPath(path);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);

            pathTransition.play();
        }
    }

    public static class MoveToAbs extends MoveTo {

        public MoveToAbs(Card card, double x, double y) {
            super(x - card.getLayoutX() + card.getLayoutBounds().getWidth() / 2, y - card.getLayoutY() + card.getLayoutBounds().getHeight() / 2);
            // recursiveTranslate(card,x - card.getLayoutX() + card.getLayoutBounds().getWidth() / 2,y - card.getLayoutY() + card.getLayoutBounds().getHeight() / 2);
        }

    }

    public static class LineToAbs extends LineTo {

        public LineToAbs(Card card, double x, double y) {
            super(x - card.getLayoutX() + card.getLayoutBounds().getWidth() / 2, y - card.getLayoutY() + card.getLayoutBounds().getHeight() / 2);
            //recursiveTranslate(card,x - card.getLayoutX() + card.getLayoutBounds().getWidth() / 2,y - card.getLayoutY() + card.getLayoutBounds().getHeight() / 2 );
        }

    }
}
