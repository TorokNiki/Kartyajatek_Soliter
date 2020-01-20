package frontEnd;

import backEnd.domain.Card;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerG implements Initializable {
    public MenuItem menuClose, UndoLastMove, showScoreBoard, startNewTure, restartCurrentTure, startNewGame, endCurrentTure, autoFinish, changeCardLook, changeCardBackLook, changeBeckgroungColor, menuGameRoles;
    public Pane pnlGameSpace, pnlApplication;
    public MenuBar menubControls;
    public TextField textScore;
    public double orgSceneX, orgSceneY;
    public double orgTranslateX, orgTranslateY;
    public int currentScore, score;
    public List<Card> doubleDeck;
    EventHandler<MouseEvent> imageViewOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((ImageView) (t.getSource())).getTranslateX();
                    orgTranslateY = ((ImageView) (t.getSource())).getTranslateY();
                }
            };
    EventHandler<MouseEvent> imageViewOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    Card source = (Card) (t.getSource());
                    recursiveTranslate(source,newTranslateX,newTranslateY);
                    source.setTranslateX(newTranslateX);
                    source.setTranslateY(newTranslateY);
                }
            };
    public void recursiveTranslate(Card source, double newTranslateX, double newTranslateY){
        Card cardOnIt = source.getCardOnIt();
        if (cardOnIt !=null){
            cardOnIt.setTranslateX(newTranslateX);
            cardOnIt.setTranslateY(newTranslateY);
            recursiveTranslate(cardOnIt,newTranslateX,newTranslateY);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuClose.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
    }
}
