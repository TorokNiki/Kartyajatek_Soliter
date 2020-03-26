package frontEnd.settings;

import backEnd.domain.enums.Backgrounds;
import backEnd.domain.enums.BackgroundImages;
import backEnd.services.factory.Config;
import backEnd.services.game.Game;
import frontEnd.MainController;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class ChangeBackgroundColour {
    private Pane paneLook;
    private ListView select;
    private Label lblInstruction;
    private Button ok, cancel;
    private Pane look;
    private List<Backgrounds> backgroundsList;
    private List<BackgroundImages> backgroundImagesList;
    private String bacground;
    private int selectedIndex;

    public ChangeBackgroundColour(Stage secondery, Game actualGame) {
        backgroundsList = Backgrounds.getBackground();
        backgroundImagesList = BackgroundImages.getBackground();
        Group root = new Group(deckLook());
        secondery.getIcons().add(new Image("img/ace.png"));
        secondery.setTitle("Játéktér szinének a kiválasztása");
        secondery.setScene(new Scene(root, 600, 400));
        secondery.setResizable(false);
        addItems();
        selectedIndex=2;
        select.scrollTo(selectedIndex);
        select.getSelectionModel().select(selectedIndex);
        select.getFocusModel().focus(selectedIndex);
       look.setStyle("-fx-background-image: url(/img/bg/"+backgroundImagesList.get(selectedIndex).getConfig()+");-fx-background-size: 200 150;");
        secondery.show();
        ok.setOnMouseClicked(mouseEvent -> {
            Config.tempBackground= backgroundImagesList.get(selectedIndex).getConfig();
            actualGame.backgoundChange();
            secondery.close();
        });
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                secondery.close();
            }
        });
        select.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedIndex = select.getSelectionModel().getSelectedIndex();
                String picture= backgroundImagesList.get(selectedIndex).getConfig();
                look.setStyle("-fx-background-image: url(/img/bg/"+picture+");-fx-background-size: 200 150;");


            }
        });
    }

    private Pane deckLook() {
        paneLook = new Pane();
        paneLook.setPrefWidth(600);
        paneLook.setPrefHeight(400);

        select = new ListView();
        select.setLayoutX(10);
        select.setLayoutY(50);
        select.setPrefHeight(300);
        select.setPrefWidth(200);

        lblInstruction = new Label();
        lblInstruction.setLayoutY(20);
        lblInstruction.setLayoutX(10);
        lblInstruction.setText("Játéktér szinének a kiválasztása");

        ok = new Button();
        ok.setLayoutX(500);
        ok.setLayoutY(300);
        ok.setPrefWidth(60);
        ok.setText("OK");

        cancel = new Button();
        cancel.setLayoutX(500);
        cancel.setLayoutY(350);
        cancel.setText("Mégsem");

        look = new Pane();
        look.setLayoutX(300);
        look.setLayoutY(100);
        look.setPrefHeight(150);
        look.setPrefWidth(200);

        paneLook.getChildren().add(select);
        paneLook.getChildren().add(lblInstruction);
        paneLook.getChildren().add(ok);
        paneLook.getChildren().add(cancel);
        paneLook.getChildren().add(look);

        return paneLook;
    }

    private void addItems() {
//        for (int i = 0; i < backgroundList.size(); i++) {
//            select.getItems().add(backgroundList.get(i).getName());
//        }
        for (int i = 0; i < backgroundImagesList.size(); i++) {
            select.getItems().add(backgroundImagesList.get(i).getName());
        }

    }
}
