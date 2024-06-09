package scrabble.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeWindowController {

	@FXML
	private void handlePlayBtnClicked(ActionEvent event) throws IOException {
	
		  Parent root = FXMLLoader.load(getClass().getResource("/ScrabbleGame.fxml"));
		  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  Scene scene = new Scene(root,1920,1080);
	      scene.getStylesheets().add("/css/game-page.css");

		  stage.setScene(scene);
		  stage.show();
	}
	
	@FXML 
	private void handleQuitButton(ActionEvent event) {
	    Platform.exit(); 
	}
	

}
