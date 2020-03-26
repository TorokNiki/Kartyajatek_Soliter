package frontEnd.settings;

import backEnd.services.database.SQLite;
import frontEnd.MainController;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

public class Alerts {
    public void win() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Gratulálok Nyertél");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        ImageView trophy = new ImageView();
        Image picture = new Image("img/trophy.png");
        trophy.setImage(picture);
        trophy.setFitHeight(500);
        trophy.setFitWidth(400);
        grid.add(trophy, 0, 0);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(picture);
        dialog.getDialogPane().setContent(grid);
        Optional<Pair<String, String>> result = dialog.showAndWait();

    }

    public void score(int score, int currentScore) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Jellenlegi pont állás");
        alert.setHeaderText(null);
        String output = String.format("Őssz pontszám: %d\nMost elért pontszám: %d", score, currentScore);
        alert.setContentText(output);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        Image icon = new Image("img/cards/icon/ace-of-spades.png");
        stage.getIcons().add(icon);
        alert.getButtonTypes().add(ButtonType.OK);
        alert.showAndWait();
    }

    public void getName(MainController controller) {
        SQLite sqLite = new SQLite();
        TextInputDialog dialog = new TextInputDialog();
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        Image icon = new Image("img/ace.png");
        stage.getIcons().add(icon);
        dialog.setTitle("Játékos neve");
        dialog.setHeaderText("");
        dialog.setContentText("Add meg a neved:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("Your name: " + result.get());
            ScoreBoard.playerName = result.get();
            sqLite.insert(ScoreBoard.playerName, controller.getScore());
        } else {
            ScoreBoard.playerName = "";
            System.out.println("Your name: " + ScoreBoard.playerName);
            sqLite.insert(ScoreBoard.playerName, controller.getScore());
        }
    }

}
