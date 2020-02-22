package backEnd.services;

import backEnd.domain.Card;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class MouseGestures {
    public double orgSceneX, orgSceneY;
    public double orgTranslateX, orgTranslateY;


    public void MouseGestures(final Card card) {

        card.setOnMousePressed(onMousePressedEventHandler);
        card.setOnMouseDragged(onMouseDraggedEventHandler);
        card.setOnMouseReleased(onMouseReleasedEventHandler);

    }
    EventHandler<MouseEvent> onMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Card source = (Card) (t.getSource());
                    if (source.isFaceup()) {
                        orgSceneX = t.getSceneX();
                        orgSceneY = t.getSceneY();

                        orgTranslateX = source.getTranslateX();
                        orgTranslateY = source.getTranslateY();
                    }
                }
            };
    EventHandler<MouseEvent> onMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Card source = (Card) (t.getSource());
                    if (source.isFaceup()) {
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

            moveToSource(card);
            recursiveTranslateBack(card);
//            if (!card.isColorEqual(card.getCardOnIt())){
//                fixPosition(card);
//            }
//            card.setEffect(null);
        }
    };

    private void fixPosition(Card card) {

        double x = card.getTranslateX();
        double y = card.getTranslateY();

        card.relocate(card.getLayoutX() + x, card.getLayoutY() + y);

        card.setTranslateX(0);
        card.setTranslateY(0);

    }

    private void moveToSource(Card card) {
        double sourceX = card.getLayoutX() + card.getTranslateX();
        double sourceY = card.getLayoutY() + card.getTranslateY();

        double targetX = card.getLayoutX();
        double targetY = card.getLayoutY();

        if (!(sourceX==targetX&&sourceY==targetY)) {
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

            if (!(sourceX==targetX&&sourceY==targetY)) {
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

    public static class MoveToAbs extends MoveTo {

        public MoveToAbs(Card card, double x, double y) {
            super(x - card.getLayoutX() + card.getLayoutBounds().getWidth() / 2, y - card.getLayoutY() + card.getLayoutBounds().getHeight() / 2);
           // recursiveTranslate(card,x - card.getLayoutX() + card.getLayoutBounds().getWidth() / 2,y - card.getLayoutY() + card.getLayoutBounds().getHeight() / 2);
        }

    }

    public static class LineToAbs extends LineTo {

        public LineToAbs(Card card, double x, double y) {
            super(x - card.getLayoutX() + card.getLayoutBounds().getWidth() / 2, y - card.getLayoutY() + card.getLayoutBounds().getHeight() / 2 );
           //recursiveTranslate(card,x - card.getLayoutX() + card.getLayoutBounds().getWidth() / 2,y - card.getLayoutY() + card.getLayoutBounds().getHeight() / 2 );
        }

    }
}