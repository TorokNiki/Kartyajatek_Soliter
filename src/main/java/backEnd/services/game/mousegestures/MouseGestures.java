package backEnd.services.game.mousegestures;

import backEnd.domain.enums.ActionType;
import backEnd.domain.Card;
import frontEnd.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class MouseGestures {

    public double orgTranslateX;
    public double orgTranslateY;
    public double orgSceneX;
    public double orgSceneY;
    public int db = 0;
    public MainController mainController;



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

    EventHandler<ActionEvent> onUndo;
    EventHandler<MouseEvent> onMousePressedEventHandler;
    EventHandler<MouseEvent> onMouseDraggedEventHandler;
    EventHandler<MouseEvent> onMouseReleasedEventHandler;

    public MouseGestures(MainController mainController) {
        this.mainController = mainController;
    }

    public void setMouseGestures(final Card card) {
        card.setOnMousePressed(onMousePressedEventHandler);
        card.setOnMouseDragged(onMouseDraggedEventHandler);
        card.setOnMouseReleased(onMouseReleasedEventHandler);
    }

}
