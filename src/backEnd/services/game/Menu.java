package backEnd.services.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Menu {
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

    public void startNext(ActionEvent actionEvent, FXMLLoader fxmlLoader) {
        try {

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Kártyajáték (Solitaire)");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            System.err.println("The window can't be opened!\n" + e);
        }

    }
}
