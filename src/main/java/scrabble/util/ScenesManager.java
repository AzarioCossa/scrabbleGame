package scrabble.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import scrabble.controller.JokerLetterSelectionController;
import scrabble.model.JokerTile;

import java.io.IOException;

/**
 * The ScenesManager class provides utility methods for managing scenes and dialogs in the Scrabble game.
 */
public class ScenesManager {

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private ScenesManager() {}

    /**
     * Shows a dialog for selecting a letter for a Joker tile.
     * 
     * @param jokerTile the Joker tile for which the letter is being selected
     */
    public static void showJokerLetterSelectionAlert(JokerTile jokerTile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ScenesManager.class.getResource("/JokerLetterSelection.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Joker Letter Selection");
            JokerLetterSelectionController controller = fxmlLoader.getController();
            controller.setJokerTile(jokerTile);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    /**
     * Shows an error dialog with the specified error message.
     * 
     * @param errorMessage the error message to be displayed
     */
    public static void showError(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
