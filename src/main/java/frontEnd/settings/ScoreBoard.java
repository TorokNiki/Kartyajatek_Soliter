package frontEnd.settings;

import backEnd.services.database.SQLite;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class ScoreBoard {
    private Button ok,clear;
    private Pane scoreBoard;
    private TableView table;
    private TableColumn rank,name,point;
    private List<Integer> rankList;
    private SQLite database;

    public ScoreBoard(Stage secondery) {
        database=new SQLite();
        rankList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rankList.add(i+1);
        }
        database.selectAll();
        Group root = new Group(scoreBoard());
        secondery.getIcons().add(new Image("img/ace.png"));
        secondery.setTitle("Eredmény tábla");
        secondery.setScene(new Scene(root, 270, 400));
        secondery.setResizable(false);
        secondery.show();
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            database.insert("asd",1);
            }
        });
        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }
    private Pane scoreBoard(){
        scoreBoard= new Pane();
        scoreBoard.setPrefHeight(400);
        scoreBoard.setPrefWidth(270);

        table= new TableView();
        table.setLayoutX(16);
        table.setLayoutY(32);
        table.setPrefHeight(300);
        table.setPrefWidth(230);


        rank= new TableColumn();
        rank.setResizable(false);
        rank.setPrefWidth(75);
        rank.setText("Hejezés");
        name= new TableColumn();
        name.setPrefWidth(75);
        name.setText("Név");
        name.setEditable(true);
        point= new TableColumn();
        point.setResizable(false);
        point.setPrefWidth(75);
        point.setText("Pontszám");

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
    private void addData(){
        database.selectAll();
     //   rank.setCellValueFactory((TableColumn.CellDataFeatures cellDataFeatures) -> {
          //  Integer rowIndex = cellDataFeatures.getValue();
          //  return new ReadOnlyIntegerWrapper(rankList.get(rowIndex));});
    }
}
