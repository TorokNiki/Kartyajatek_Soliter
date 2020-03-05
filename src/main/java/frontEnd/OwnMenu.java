package frontEnd;

import frontEnd.MainController;
import frontEnd.settings.*;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OwnMenu {
    private MenuBar menuBar;
    private MenuItem menuItem;
    private static Stage secondery;
    private MainController mainController;

    public OwnMenu(ReadOnlyDoubleProperty stageWidth,MainController mainController) {
        this.menuBar = createDefaultMenuBar(stageWidth);
        this.menuItem = new MenuItem();
        this.mainController=mainController;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    private MenuBar createDefaultMenuBar(ReadOnlyDoubleProperty width) {
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("Fájl");
        menuBar.getMenus().add(file);
        Menu ture = new Menu("Túra");
        menuBar.getMenus().add(ture);
        Menu options = new Menu("Opciók");
        menuBar.getMenus().add(options);
        Menu help = new Menu("Segitség");
        menuBar.getMenus().add(help);
        menuBar.prefWidthProperty().bind(width);
        setMenuItemsFile(file);
        setMenuItemsTure(ture);
        setMenuItemsOptions(options);
        setMenuItemsHelp(help);
        return menuBar;
    }

    private void setMenuItemsFile(Menu menu) {
        secondery=new Stage();
        javafx.scene.control.MenuItem miUndoLastMove = new javafx.scene.control.MenuItem("Utolsó lépés visszavonása");
        javafx.scene.control.MenuItem miShowScoreBoard = new javafx.scene.control.MenuItem("Eredménytábla");
        javafx.scene.control.SeparatorMenuItem separatorMenuItem=new javafx.scene.control.SeparatorMenuItem();
        javafx.scene.control.MenuItem miExit = new MenuItem("Bezár");
        miExit.setOnAction(o -> System.exit(0));
        miShowScoreBoard.setOnAction(o -> new ScoreBoard(secondery));
        menu.getItems().addAll(miUndoLastMove, miShowScoreBoard,separatorMenuItem, miExit);
    }

    private void setMenuItemsTure(Menu menu) {
        MenuItem miStartNewTure = new MenuItem("Új Túra inditása");
        miStartNewTure.setOnAction(o-> mainController.restartTure());
        MenuItem miRestartCurrentTure = new MenuItem("Aktuális Túra újraindítása");
        miRestartCurrentTure.setOnAction(o-> mainController.restartGame());
        MenuItem miStartNewGame = new MenuItem("Következő játék indítása");
        miStartNewGame.setOnAction(o-> mainController.goToNextGame());
        MenuItem miEndCurrentTure = new MenuItem("Túra befejezése");
        miEndCurrentTure.setOnAction(o -> new Alerts().score(mainController.getScore(),mainController.getCurrentScore()));
        menu.getItems().addAll(miStartNewTure, miRestartCurrentTure, miStartNewGame, miEndCurrentTure);
    }

    private  void setMenuItemsOptions(Menu menu) {
        secondery= new Stage();
        MenuItem miChangeCardLook = new MenuItem("Kártya kinézete");
        miChangeCardLook.setOnAction(o->new ChangeDeckLook(secondery, mainController.actualGame));
        MenuItem miChangeCardBack = new MenuItem("Kártya hátlapja");
        miChangeCardBack.setOnAction(o->new ChangeCardBack(secondery,mainController.actualGame));
        MenuItem miChangeBackgroundColour = new MenuItem("Játékterület szine");
        miChangeBackgroundColour.setOnAction(o->new ChangeBackgroundColour(secondery,mainController.actualGame));
        menu.getItems().addAll(miChangeCardLook, miChangeCardBack, miChangeBackgroundColour);
    }

    private void setMenuItemsHelp(Menu menu) {
        MenuItem miRools = new MenuItem("Játékszabály");
        miRools.setOnAction(o -> rules());
        menu.getItems().addAll(miRools);
    }

    public void rules() {
        openRools();
    }

    public void openRools() {
        File pdfFile = new File("..\\Kartyajatek_Solitaire\\src\\main\\resources\\rools.pdf");
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

    public javafx.scene.control.Label setLabel(javafx.scene.control.Label gameName,String text,String colour) {
        gameName.setAlignment(Pos.CENTER);
        gameName.setLayoutX(584);
        gameName.setLayoutY(5);
        gameName.prefHeight(13);
        gameName.prefWidth(204);
        gameName.setText(text);
        gameName.setTextFill(Color.valueOf(colour));
        return gameName;
    }

    public TextField setTFScore(TextField score,int allScore,int currentScore) {
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
