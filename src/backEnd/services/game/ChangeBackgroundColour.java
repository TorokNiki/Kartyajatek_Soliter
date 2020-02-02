package backEnd.services.game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChangeBackgroundColour {
    private Pane paneLook;
    private ListView select;
    private Label lblInstruction;
    private Button ok,cancel;
    private ImageView look;

    public ChangeBackgroundColour(Stage secondery) {
        Group root = new Group(deckLook());
        secondery.getIcons().add(new Image("./resources/img/ace.png"));
        secondery.setTitle("Játéktér szinének a kiválasztása");
        secondery.setScene(new Scene(root, 600, 400));
        secondery.setResizable(false);
        secondery.show();
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                secondery.close();
            }
        });
    }

    private Pane deckLook(){
        paneLook= new Pane();
        paneLook.setPrefWidth(600);
        paneLook.setPrefHeight(400);

        select=new ListView();
        select.setLayoutX(10);
        select.setLayoutY(50);
        select.setPrefHeight(300);
        select.setPrefWidth(200);

        lblInstruction=new Label();
        lblInstruction.setLayoutY(20);
        lblInstruction.setLayoutX(10);
        lblInstruction.setText("Játéktér szinének a kiválasztása");

        ok= new Button();
        ok.setLayoutX(500);
        ok.setLayoutY(300);
        ok.setPrefWidth(60);
        ok.setText("OK");

        cancel=new Button();
        cancel.setLayoutX(500);
        cancel.setLayoutY(350);
        cancel.setText("Mégsem");

        look= new ImageView();
        look.setLayoutX(300);
        look.setLayoutY(100);
        look.setFitHeight(150);
        look.setFitWidth(200);

        paneLook.getChildren().add(select);
        paneLook.getChildren().add(lblInstruction);
        paneLook.getChildren().add(ok);
        paneLook.getChildren().add(cancel);
        paneLook.getChildren().add(look);

        return paneLook;
    }
}
