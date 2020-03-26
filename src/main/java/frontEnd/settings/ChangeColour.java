package frontEnd.settings;

import backEnd.services.factory.Config;
import backEnd.services.game.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChangeColour {
    private Pane paneLook;
    private Button ok, cancel;
    private ColorPicker colorPicker;
    private Pane look;
    private Text text;
    private String colour;

    public ChangeColour(Stage secondery, Game actualGame) {
        Group root = new Group(deckLook());
        secondery.getIcons().add(new Image("img/ace.png"));
        secondery.setTitle("Játéktér szinének a kiválasztása");
        secondery.setScene(new Scene(root, 600, 400));
        secondery.setResizable(false);
        colour = colorPicker.getValue().toString();
        colour = colour.replaceFirst("0x", "#");
        look.setStyle("-fx-background-color: " + colour + ";");
        secondery.show();

        colorPicker.setOnAction(actionEvent -> {
            text.setFill(colorPicker.getValue());
            colour = colorPicker.getValue().toString();
            colour = colour.replaceFirst("0x", "#");
            look.setStyle("-fx-background-color: " + colour + ";");
        });

        ok.setOnMouseClicked(mouseEvent -> {
            Config.tempBackground = "color";
            Config.tempBackgroundcolor = colour;
            actualGame.backgoundChange();
            secondery.close();
        });

        cancel.setOnMouseClicked(mouseEvent -> secondery.close());
    }

    private Pane deckLook() {
        paneLook = new Pane();
        paneLook.setPrefWidth(600);
        paneLook.setPrefHeight(400);

        ok = new Button();
        ok.setLayoutX(250);
        ok.setLayoutY(350);
        ok.setPrefWidth(60);
        ok.setText("OK");

        cancel = new Button();
        cancel.setLayoutX(350);
        cancel.setLayoutY(350);
        cancel.setText("Mégsem");

        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.LIGHTBLUE);

        text = new Text("A játéktér szinének a kiválasztása");
        text.setLayoutY(20);
        text.setLayoutX(150);
        text.setFont(Font.font("Verdana", 20));
        text.setFill(colorPicker.getValue());


        look = new Pane();
        look.setLayoutX(50);
        look.setLayoutY(50);
        look.setPrefHeight(200);
        look.setPrefWidth(400);

        paneLook.getChildren().add(colorPicker);
        paneLook.getChildren().add(ok);
        paneLook.getChildren().add(look);
        paneLook.getChildren().add(cancel);
        paneLook.getChildren().add(text);

        return paneLook;
    }
}
