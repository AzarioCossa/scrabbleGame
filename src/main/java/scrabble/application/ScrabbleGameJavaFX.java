package scrabble.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScrabbleGameJavaFX extends Application{
	@Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file from the resources
		Parent root = FXMLLoader.load(getClass().getResource("/ScrabbleGame.fxml"));
        primaryStage.setTitle("War Card Game in JavaFX");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        }
	
	public static void main(String[] args) {
		launch(args);
	}
}