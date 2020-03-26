package frontEnd.settings;

import backEnd.domain.enums.Front;
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

public class ChangeDeckLook {
    private Pane deckLook;
    private ListView select;
    private Label lblInstruction;
    private Button ok, cancel;
    private ImageView look, look1, look2;
    private List<Front> frontCardList;
    private int selectedIndex;

    public ChangeDeckLook(Stage primaryStage, Game actualGame) {
        frontCardList = Front.getFrontList();
        Group root = new Group(deckLook());
        primaryStage.getIcons().add(new Image("img/flipp.png"));
        primaryStage.setTitle("Kártyák kinézetének a kiválasztása");
        primaryStage.setScene(new Scene(root, 550, 400));
        primaryStage.setResizable(false);
        addItems();
        selectedIndex = 4;
        select.scrollTo(selectedIndex);
        select.getSelectionModel().select(selectedIndex);
        select.getFocusModel().focus(selectedIndex);
        look.setImage(new Image(frontCardList.get(selectedIndex).getPicture1()));
        look1.setImage(new Image(frontCardList.get(selectedIndex).getPicture2()));
        look2.setImage(new Image(frontCardList.get(selectedIndex).getPicture3()));
        primaryStage.show();
        ok.setOnMouseClicked(mouseEvent -> {
            Config.tempFront = frontCardList.get(selectedIndex).getConfig();
            actualGame.cardLookChange();
            primaryStage.close();

        });
        cancel.setOnMouseClicked(mouseEvent -> primaryStage.close());
        select.setOnMouseClicked(mouseEvent -> {
            selectedIndex = select.getSelectionModel().getSelectedIndex();
            look.setImage(new Image(frontCardList.get(selectedIndex).getPicture1()));
            look1.setImage(new Image(frontCardList.get(selectedIndex).getPicture2()));
            look2.setImage(new Image(frontCardList.get(selectedIndex).getPicture3()));
        });
    }

    private Pane deckLook() {
        deckLook = new Pane();
        deckLook.setPrefWidth(500);
        deckLook.setPrefHeight(400);

        select = new ListView();
        select.setLayoutX(10);
        select.setLayoutY(50);
        select.setPrefHeight(300);
        select.setPrefWidth(200);

        lblInstruction = new Label();
        lblInstruction.setLayoutY(20);
        lblInstruction.setLayoutX(10);
        lblInstruction.setText("Kártya stílus kiválasztása");

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
        look.setLayoutX(250);
        look.setLayoutY(100);
        look.setFitHeight(200);
        look.setFitWidth(140);
        look1 = new ImageView();
        look1.setLayoutX(360);
        look1.setLayoutY(100);
        look1.setFitHeight(200);
        look1.setFitWidth(140);
        look2 = new ImageView();
        look2.setLayoutX(280);
        look2.setLayoutY(20);
        look2.setFitHeight(200);
        look2.setFitWidth(140);

        deckLook.getChildren().addAll(select, lblInstruction, ok, cancel, look1, look2, look);
        return deckLook;
    }

    private void addItems() {
        for (int i = 0; i < frontCardList.size(); i++) {
            select.getItems().add(frontCardList.get(i).getName());
        }
    }
}
