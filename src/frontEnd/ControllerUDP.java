package frontEnd;

import backEnd.domain.Card;
import backEnd.domain.Rank;
import backEnd.services.factory.DeckFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerUDP implements Initializable {
    public Pane pnlGameSpace, pnlApplication;
    public MenuBar menubControls;
    public TextField textScore;
    public MenuItem menuClose, UndoLastMove, showScoreBoard, startNewTure, restartCurrentTure, startNewGame, endCurrentTure, autoFinish, changeCardLook, changeCardBackLook, changeBeckgroungColor, menuGameRoles;
    public double orgSceneX, orgSceneY;
    public double orgTranslateX, orgTranslateY;
    public int currentScore, score;
    public List<Card> doubleDeck;

    EventHandler<MouseEvent> imageViewOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((ImageView) (t.getSource())).getTranslateX();
                    orgTranslateY = ((ImageView) (t.getSource())).getTranslateY();
                }
            };
    EventHandler<MouseEvent> imageViewOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    Card source = (Card) (t.getSource());
                    recursiveTranslate(source, newTranslateX, newTranslateY);
                    source.setTranslateX(newTranslateX);
                    source.setTranslateY(newTranslateY);
                }
            };

    public void recursiveTranslate(Card source, double newTranslateX, double newTranslateY) {
        Card cardOnIt = source.getCardOnIt();
        if (cardOnIt != null) {
            cardOnIt.setTranslateX(newTranslateX);
            cardOnIt.setTranslateY(newTranslateY);
            recursiveTranslate(cardOnIt, newTranslateX, newTranslateY);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentScore = 0;
        score = 0;
        menuClose.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        menuGameRoles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
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
        });
        doubleDeck = new DeckFactory().doubleDeck();
        placeCardsOnBoard();
    }

    public void Indit(ActionEvent actionEvent) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("threeShuffelsAndADraw.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Tarsasjatek");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            System.err.println("Az ablakot nem lehet megnyitni!\n" + e);
        }

//        Stage currentStage = (Stage) btnJatekInditasa.getScene().getWindow();
//        currentStage.close();
    }

    public void placeCardsOnBoard() {
        emptySpace(15, 2);
        emptySpace(140, 10);

        Card[] ase = new Card[8];
        int index = 0;
        for (int i = 0; i < doubleDeck.size(); i++) {
            if (doubleDeck.get(i).getRank().equals(Rank.ACE)) {
                ase[index] = doubleDeck.get(i);
                doubleDeck.remove(doubleDeck.get(i));
                index++;
            }
        }

        for (int i = 2; i < 10; i++) {
            Card card = ase[i - 2];
            card.setImage(card.getFront());
            card.setFitWidth(70);
            card.setLayoutX((i * card.getFitWidth() + i * 10) + 5);
            card.setLayoutY(10);
            card.setPreserveRatio(true);
            pnlGameSpace.getChildren().add(card);
        }
        Collections.shuffle(doubleDeck);
        int actual = 0;
        Card card = null;
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= i * 2 + 1; j++) {
                actual = placeCards(actual, i, j);
                if (card != null) {
                    card.setCardOnIt(doubleDeck.get(actual));
                }
                card = doubleDeck.get(actual);
                actual++;
            }
            card = null;
        }
        for (int i = 5; i < 10; i++) {
            for (int j = 1; j <= (10 - i) * 2; j++) {
                actual = placeCards(actual, i, j);
                if (card != null) {
                    card.setCardOnIt(doubleDeck.get(actual));
                }
                card = doubleDeck.get(actual);
                actual++;
            }
            card = null;
        }
        for (int i = 0; i <= 40; i++) {
            doubleDeck.get(actual).setFitWidth(70);
            doubleDeck.get(actual).setFitHeight(110);
            doubleDeck.get(actual).setLayoutX(((i * 0.005) * doubleDeck.get(actual).getFitWidth()) + 5);
            doubleDeck.get(actual).setLayoutY(10);
            doubleDeck.get(actual).setImage(doubleDeck.get(actual).getBack());
//            doubleDeck.get(actual).setOnMousePressed(imageViewOnMousePressedEventHandler);
//            doubleDeck.get(actual).setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
            pnlGameSpace.getChildren().add(doubleDeck.get(actual));
            actual++;
        }


    }

    private int placeCards(int actual, int i, int j) {
        doubleDeck.get(actual).setFitWidth(70);
        doubleDeck.get(actual).setFitHeight(110);
        doubleDeck.get(actual).setLayoutX((i * doubleDeck.get(actual).getFitWidth() + i * 10) + 5);
        doubleDeck.get(actual).setLayoutY(j * 25 + 110);
        doubleDeck.get(actual).setImage(doubleDeck.get(actual).getFront());
        doubleDeck.get(actual).setOnMousePressed(imageViewOnMousePressedEventHandler);
        doubleDeck.get(actual).setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
        pnlGameSpace.getChildren().add(doubleDeck.get(actual));
        return actual;
    }

    private void emptySpace(int y, int length) {
        for (int i = 0; i < length; i++) {
            ImageView empty = new ImageView();
            Image img = new Image("./resources/img/kuka.png");
            empty.setImage(img);
            empty.setFitWidth(70);
            empty.setLayoutX((i * empty.getFitWidth() + i * 10) + 5);
            empty.setLayoutY(y);
            empty.setPreserveRatio(true);
            pnlGameSpace.getChildren().add(empty);
        }
    }

    private void win() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Gratulálok Nyertél");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        ImageView trophy = new ImageView();
        Image picture = new Image("./resources/img/trophy.png");
        trophy.setImage(picture);
        trophy.setFitHeight(500);
        trophy.setFitWidth(400);
        grid.add(trophy, 0, 0);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(picture);
        dialog.getDialogPane().setContent(grid);
        Optional<Pair<String, String>> result = dialog.showAndWait();

    }

    private void score() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Jellenlegi pont állás");
        alert.setHeaderText(null);
        String output = String.format("Őssz pontszám: %d\nMost elért pontszám: %d", score, currentScore);
        alert.setContentText(output);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        Image icon = new Image("./resources/img/cards/icon/ace-of-spades.png");
        stage.getIcons().add(icon);
        alert.getButtonTypes().add(ButtonType.OK);
        alert.showAndWait();
    }


}
