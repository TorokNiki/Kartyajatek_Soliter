package frontEnd;

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

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class OwnMenu {
    private static Stage secondery;
    private MenuBar menuBar;
    private MenuItem menuItem;
    private MainController mainController;
    private MenuItem miStartNewTure, miRestartCurrentTure, miStartNewGame, miEndCurrentTure, miUndoLastMove;

    public OwnMenu(ReadOnlyDoubleProperty stageWidth, MainController mainController) {
        this.menuBar = createDefaultMenuBar(stageWidth);
        this.menuItem = new MenuItem();
        this.mainController = mainController;
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
        secondery = new Stage();
        this.miUndoLastMove = new javafx.scene.control.MenuItem("Utolsó lépés visszavonása");
        setUndoEnabled();
        javafx.scene.control.MenuItem miShowScoreBoard = new javafx.scene.control.MenuItem("Eredménytábla");
        javafx.scene.control.SeparatorMenuItem separatorMenuItem = new javafx.scene.control.SeparatorMenuItem();
        javafx.scene.control.MenuItem miExit = new MenuItem("Bezár");
        miExit.setOnAction(o -> System.exit(0));
        miShowScoreBoard.setOnAction(o -> new ScoreBoard(secondery));
        menu.getItems().addAll(miUndoLastMove, miShowScoreBoard, separatorMenuItem, miExit);
    }

    public void setUndoAction() {
        this.miUndoLastMove.setOnAction(mainController.actualGame.getMouseGestures().getOnUndo());
    }

    public void setUndoDisabled() {
        miUndoLastMove.setDisable(true);
    }

    public void setUndoEnabled() {
        miUndoLastMove.setDisable(false);
    }

    private void setMenuItemsTure(Menu menu) {
        this.miStartNewTure = new MenuItem("Új Túra inditása");
        this.miRestartCurrentTure = new MenuItem("Aktuális Túra újraindítása");
        this.miStartNewGame = new MenuItem("Következő játék indítása");
        this.miEndCurrentTure = new MenuItem("Túra befejezése");
        miStartNewTure.setOnAction(o -> {
            mainController.restartTure();
            disabledMenuItems(miRestartCurrentTure, miStartNewGame, miEndCurrentTure, false);
        });
        miRestartCurrentTure.setOnAction(o -> mainController.restartGame());
        miStartNewGame.setOnAction(o -> mainController.goToNextGame());
        miEndCurrentTure.setOnAction((ActionEvent o) -> {
            new Alerts().score(mainController.getScore(), mainController.getCurrentScore());
            new Alerts().getName(mainController);
            new ScoreBoard(secondery);
            disabledMenuItems(miRestartCurrentTure, miStartNewGame, miEndCurrentTure, true);
        });
        menu.getItems().addAll(miStartNewTure, miRestartCurrentTure, miStartNewGame, miEndCurrentTure);
    }

    public void disabledMenuItems(MenuItem miRestartCurrentTure, MenuItem miStartNewGame, MenuItem miEndCurrentTure, boolean b) {
        miRestartCurrentTure.setDisable(b);
        miStartNewGame.setDisable(b);
        miEndCurrentTure.setDisable(b);
        mainController.actualGame.getBoard().setDisable(b);
    }

    public MenuItem getMiStartNewTure() {
        return miStartNewTure;
    }

    public MenuItem getMiRestartCurrentTure() {
        return miRestartCurrentTure;
    }

    public MenuItem getMiStartNewGame() {
        return miStartNewGame;
    }

    public MenuItem getMiEndCurrentTure() {
        return miEndCurrentTure;
    }

    private void setMenuItemsOptions(Menu menu) {
        secondery = new Stage();
        MenuItem miChangeCardLook = new MenuItem("Kártya kinézete");
        miChangeCardLook.setOnAction(o -> new ChangeDeckLook(secondery, mainController.actualGame));
        MenuItem miChangeCardBack = new MenuItem("Kártya hátlapja");
        miChangeCardBack.setOnAction(o -> new ChangeCardBack(secondery, mainController.actualGame));
        MenuItem miChangeBackgroundColour = new MenuItem("Játékterület szine");
        miChangeBackgroundColour.setOnAction(o -> new ChangeBackgroundColour(secondery, mainController.actualGame));
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
        try {
            String inputPdf = "roolsdocx.pdf";
            Path tempOutput = Files.createTempFile("roolsdocx", ".pdf");
            tempOutput.toFile().deleteOnExit();
            System.out.println("tempOutput: " + tempOutput);
            try (InputStream is = OwnMenu.class.getClassLoader().getResourceAsStream(inputPdf)) {
                Files.copy(is, tempOutput, StandardCopyOption.REPLACE_EXISTING);
            }
            Desktop.getDesktop().open(tempOutput.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public javafx.scene.control.Label setLabel(javafx.scene.control.Label gameName, String text, String colour) {
        gameName.setAlignment(Pos.CENTER);
        gameName.setLayoutX(584);
        gameName.setLayoutY(5);
        gameName.prefHeight(13);
        gameName.prefWidth(204);
        gameName.setText(text);
        gameName.setTextFill(Color.valueOf(colour));
        return gameName;
    }

    public TextField setTFScore(TextField score, int allScore, int currentScore) {
        score.setText(currentScore + "/" + allScore);
        score.setAlignment(Pos.CENTER);
        score.setEditable(false);
        score.setFocusTraversable(false);
        score.setLayoutX(336.0);
        return score;
    }
}
