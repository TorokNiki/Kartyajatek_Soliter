package backEnd.services.game.mousegestures;

import backEnd.domain.enums.ActionType;
import backEnd.domain.Card;
import frontEnd.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class MouseGestures {

    protected double orgTranslateX;
    protected double orgTranslateY;
    protected double orgSceneX;
    protected double orgSceneY;
    public int db = 0;
    protected MainController mainController;



    protected ActionType actionType;
    protected Card sorceCard;
    protected Card targetCard;
    protected Card movedCard;
    protected double cardX;
    protected double cardY;
    protected double cardXtemp;
    protected double cardYtemp;

    public EventHandler<ActionEvent> getOnUndo() {
        return onUndo;
    }

    protected EventHandler<ActionEvent> onUndo;
    protected EventHandler<MouseEvent> onMousePressedEventHandler;
    protected EventHandler<MouseEvent> onMouseDraggedEventHandler;
    protected EventHandler<MouseEvent> onMouseReleasedEventHandler;

    public MouseGestures(MainController mainController) {
        this.mainController = mainController;
    }

    public void setMouseGestures(final Card card) {
        card.setOnMousePressed(onMousePressedEventHandler);
        card.setOnMouseDragged(onMouseDraggedEventHandler);
        card.setOnMouseReleased(onMouseReleasedEventHandler);
    }

}
