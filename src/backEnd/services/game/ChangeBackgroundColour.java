package backEnd.services.game;

import backEnd.domain.Background;
import frontEnd.Controller;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class ChangeBackgroundColour {
    private Pane paneLook;
    private ListView select;
    private Label lblInstruction;
    private Button ok, cancel;
    private Pane look;
    private List<Background> backgroundList;
    private String bacground;
    private Controller c;

    public ChangeBackgroundColour(Stage secondery) {
        c = new Controller();
        backgroundList = Background.getBackground();
        Group root = new Group(deckLook());
        secondery.getIcons().add(new Image("./resources/img/ace.png"));
        secondery.setTitle("Játéktér szinének a kiválasztása");
        secondery.setScene(new Scene(root, 600, 400));
        secondery.setResizable(false);
        addItems();
        secondery.show();
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                c.setBackColour(bacground);
//                c.upsideDownPyramid();
//                secondery.close();
            }
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
                int selectedIndex = select.getSelectionModel().getSelectedIndex();
                look.setStyle(backgroundList.get(selectedIndex).getColour());
                bacground = backgroundList.get(selectedIndex).getColour();

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
        for (int i = 0; i < backgroundList.size(); i++) {
            select.getItems().add(backgroundList.get(i).getName());
        }
    }
}
