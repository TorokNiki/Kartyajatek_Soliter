package frontEnd.settings;

import backEnd.services.database.SQLite;
import backEnd.services.database.Score;
import frontEnd.MainController;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;


public class ScoreBoard {
    private Button ok,clear;
    private Pane scoreBoard;
    private TableView table;
    private TableColumn rank,name,point;
    private SQLite database;
    private List<String> names,scores;
    public static String playerName;

    public ScoreBoard(Stage secondery, MainController controller) {
        database=new SQLite();
        names=database.selectName();
        scores=database.selectScore();
        Group root = new Group(scoreBoard());
        for (int i = 0; i < 10; i++) {
            if (names.size()>i&&scores.size()>i) {
                table.getItems().add(new Score(i +1 + ".", names.get(i),scores.get(i)));

            }else {
                table.getItems().add(new Score(i +1 + "."));
            }
        }
        secondery.getIcons().add(new Image("img/trophy.png"));
        secondery.setTitle("Eredmény tábla");

        secondery.setScene(new Scene(root, 270, 400));
        secondery.setResizable(false);
        secondery.show();
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                secondery.close();
            }
        });
        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                database.deleteAll();
                secondery.close();
            }
        });
    }
    private Pane scoreBoard(){
        scoreBoard= new Pane();
        scoreBoard.setPrefHeight(400);
        scoreBoard.setPrefWidth(270);

        table= new TableView<Score>();
        table.setLayoutX(16);
        table.setLayoutY(32);
        table.setPrefHeight(300);
        table.setPrefWidth(230);


        rank= new TableColumn<String, Score>();
        rank.setResizable(false);
        rank.setPrefWidth(75);
        rank.setText("Hejezés");
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        name= new TableColumn<String,Score>();
        name.setPrefWidth(75);
        name.setText("Név");
        name.setEditable(true);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        point= new TableColumn<Integer,Score>();
        point.setResizable(false);
        point.setPrefWidth(75);
        point.setText("Pontszám");
        point.setCellValueFactory(new PropertyValueFactory<>("point"));

        ok= new Button();
        ok.setLayoutX(40);
        ok.setLayoutY(350);
        ok.setText("OK");

        clear= new Button();
        clear.setLayoutX(180);
        clear.setLayoutY(350);
        clear.setText("Töröl");

        table.getColumns().addAll(rank,name,point);
        scoreBoard.getChildren().addAll(table,ok,clear);
        return scoreBoard;
    }
}
