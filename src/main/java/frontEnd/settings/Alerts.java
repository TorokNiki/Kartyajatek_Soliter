package frontEnd.settings;

import javafx.geometry.Insets;
import javafx.scene.control.*;
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

    public void scoreBoard() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Eredmény Tábla");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        Image picture = new Image("img/trophy.png");
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(picture);
        TableView table = new TableView();
        TableColumn columnID = new TableColumn();
        TableColumn columnName = new TableColumn();
        TableColumn columnScore = new TableColumn();
        columnID.setText("Helyezés");
        columnID.resizableProperty().set(false);
        columnID.setMaxWidth(70);
        columnID.setId("cId");
        columnName.setText("Név");
        columnName.resizableProperty().set(true);
        columnScore.setText("Pontszám");
        columnScore.resizableProperty().set(true);
        table.getColumns().addAll(columnID, columnName, columnScore);
        grid.add(table, 0, 0);

        ButtonType clearButtonType = new ButtonType("Töröl", ButtonBar.ButtonData.OTHER);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, clearButtonType);
        dialog.getDialogPane().setContent(grid);
        Optional<Pair<String, String>> result = dialog.showAndWait();
        //Optional<ButtonType> result = dialog.showAndWait();
//        if (result.get() == clearButtonType) {
//            try {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Megerősít");
//                alert.setHeaderText("");
//                alert.setContentText("Törli az eddigi eredményeket?");
//
//                Optional<ButtonType> result2 = alert.showAndWait();
//                if (result2.get() == ButtonType.OK){
//                    // ... user chose OK
//                } else {
//                    // ... user chose CANCEL or closed the dialog
//                }
//            } catch (Exception e) {
//            }
//        }

    }

}
