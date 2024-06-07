package scrabble.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import scrabble.controller.JokerLetterSelectionController;
import scrabble.model.FrenchLetters;
import scrabble.model.JokerTile;

import java.io.IOException;

public class ScenesManager {
	private ScenesManager () {}

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
	   
	   public static void showError(String errorMessage) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText(null);
	        alert.setContentText(errorMessage);
	        alert.showAndWait();
	   }


}
