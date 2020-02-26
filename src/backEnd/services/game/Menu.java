package backEnd.services.game;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Menu {
    private static Stage secondery;
    public static javafx.scene.control.MenuBar getMenuBar(Stage primaryStage) {
        javafx.scene.control.MenuBar menuBar = new MenuBar();
        javafx.scene.control.Menu file = new javafx.scene.control.Menu("Fájl");
        menuBar.getMenus().add(file);
        javafx.scene.control.Menu ture = new javafx.scene.control.Menu("Túra");
        menuBar.getMenus().add(ture);
        javafx.scene.control.Menu options = new javafx.scene.control.Menu("Opciók");
        menuBar.getMenus().add(options);
        javafx.scene.control.Menu help = new javafx.scene.control.Menu("Segitség");
        menuBar.getMenus().add(help);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        setMenuItemsFile(file);
        setMenuItemsTure(ture);
        setMenuItemsOptions(options);
        setMenuItemsHelp(help);
        return menuBar;
    }

    private static void setMenuItemsFile(javafx.scene.control.Menu menu) {
        secondery=new Stage();
        javafx.scene.control.MenuItem miUndoLastMove = new javafx.scene.control.MenuItem("Utolsó lépés visszavonása");
        javafx.scene.control.MenuItem miShowScoreBoard = new javafx.scene.control.MenuItem("Eredménytábla");
        javafx.scene.control.MenuItem miExit = new MenuItem("Bezár");
        miExit.setOnAction(o -> System.exit(0));
        miShowScoreBoard.setOnAction(o -> new ScoreBoard(secondery));
        menu.getItems().addAll(miUndoLastMove, miShowScoreBoard, miExit);
    }

    private static void setMenuItemsTure(javafx.scene.control.Menu menu) {
        MenuItem miStartNewTure = new MenuItem("Új Túra inditása");
        //miStartNewTure.setOnAction(o-> new Main().start();
        //miStartNewTure.setOnAction(o->new Controller().upsideDownPyramid());
        MenuItem miRestartCurrentTure = new MenuItem("Aktuális Túra újraindítása");
        MenuItem miStartNewGame = new MenuItem("Új játék indítása");
        MenuItem miEndCurrentTure = new MenuItem("Túra befejezése");
        miEndCurrentTure.setOnAction(o -> new Alerts().score(0, 0));
        menu.getItems().addAll(miStartNewTure, miRestartCurrentTure, miStartNewGame, miEndCurrentTure);
    }

    private static void setMenuItemsOptions(javafx.scene.control.Menu menu) {
        secondery= new Stage();
        MenuItem miChangeCardLook = new MenuItem("Kártya kinézete");
        miChangeCardLook.setOnAction(o->new ChangeDeckLook(secondery));
        MenuItem miChangeCardBack = new MenuItem("Kártya hátlapja");
        miChangeCardBack.setOnAction(o->new ChangeCardBack(secondery));
        MenuItem miChangeBackgroundColour = new MenuItem("Játékterület szine");
        miChangeBackgroundColour.setOnAction(o->new ChangeBackgroundColour(secondery));
        menu.getItems().addAll(miChangeCardLook, miChangeCardBack, miChangeBackgroundColour);
    }

    private static void setMenuItemsHelp(javafx.scene.control.Menu menu) {
        MenuItem miRools = new MenuItem("Játékszabály");
        miRools.setOnAction(o -> rules());
        menu.getItems().addAll(miRools);
    }

    public static void rules() {
        new Menu().openRools();
    }

    public void openRools() {
        File pdfFile = new File("..\\Kartyajatek_Soliter\\src\\resources\\rools.pdf");
        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(pdfFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Awt Desktop is not supported!");
            }
        } else {
            System.out.println("File is not exists!");
        }
    }

    public javafx.scene.control.Label setLabel(javafx.scene.control.Label gameName) {
        gameName.setAlignment(Pos.CENTER);
        gameName.setLayoutX(584);
        gameName.setLayoutY(5);
        gameName.prefHeight(13);
        gameName.prefWidth(204);
        gameName.setText("Upside-Down Piramid (1/10)");
        gameName.setTextFill(Color.valueOf("#752777"));
        return gameName;
    }

    public javafx.scene.control.TextField setTFScore(int allScore,int currentScore) {
        TextField score=new TextField();
        score.setText(currentScore+"/"+allScore);
        score.setAlignment(Pos.CENTER);
        score.setEditable(false);
        score.setFocusTraversable(false);
        score.setLayoutX(336.0);
        return score;
    }

    public void nextGame(ActionEvent actionEvent) {


    }

    public void reStart(ActionEvent actionEvent) {

    }

    public void restart(ActionEvent actionEvent) {
        //placeCardsOnBoard();
    }
}
