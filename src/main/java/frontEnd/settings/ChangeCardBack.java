package frontEnd.settings;

import backEnd.domain.enums.Back;
import backEnd.services.factory.Config;
import backEnd.services.game.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class ChangeCardBack {
    private Pane deckLook;
    private ListView select;
    private Label lblInstruction;
    private Button ok, cancel;
    private ImageView look;
    private List<Back> tmp;
    private int selectedIndex;

    public ChangeCardBack(Stage secondery, Game actualGame) {
        tmp = Back.getBackList();
        Group root = new Group(deckLook());
        secondery.getIcons().add(new Image("img/ace.png"));
        secondery.setTitle("Kártyák hátának a kiválasztása");
        secondery.setScene(new Scene(root, 600, 400));
        secondery.setResizable(false);
        addItems();
        selectedIndex = 0;
        select.scrollTo(selectedIndex);
        select.getSelectionModel().select(selectedIndex);
        select.getFocusModel().focus(selectedIndex);
        look.setImage(new Image(tmp.get(selectedIndex).getPicture()));
        secondery.show();
        ok.setOnMouseClicked(mouseEvent -> {
            Config.tempBack = tmp.get(selectedIndex).getConfig();
            actualGame.cardLookChange();
            secondery.close();

        });
        cancel.setOnMouseClicked(mouseEvent -> secondery.close());
        select.setOnMouseClicked(mouseEvent -> {
            selectedIndex = select.getSelectionModel().getSelectedIndex();
            look.setImage(new Image(tmp.get(selectedIndex).getPicture()));
        });

    }

    private Pane deckLook() {
        deckLook = new Pane();
        deckLook.setPrefWidth(600);
        deckLook.setPrefHeight(400);

        select = new ListView();
        select.setLayoutX(10);
        select.setLayoutY(50);
        select.setPrefHeight(300);
        select.setPrefWidth(200);

        lblInstruction = new Label();
        lblInstruction.setLayoutY(20);
        lblInstruction.setLayoutX(10);
        lblInstruction.setText("Kártya hátlap kiválasztása");

        ok = new Button();
        ok.setLayoutX(250);
        ok.setLayoutY(350);
        ok.setPrefWidth(60);
        ok.setText("OK");

        cancel = new Button();
        cancel.setLayoutX(350);
        cancel.setLayoutY(350);
        cancel.setText("Mégsem");

        look = new ImageView();
        look.setLayoutX(300);
        look.setLayoutY(100);
        look.setFitHeight(200);
        look.setFitWidth(140);

        deckLook.getChildren().addAll(select, lblInstruction, ok, cancel, look);

        return deckLook;
    }

    private void addItems() {
        for (int i = 0; i < tmp.size(); i++) {
            select.getItems().add(tmp.get(i).getName());
        }
    }

}
