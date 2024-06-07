package scrabble.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ScrabbleGameJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file from the resources

        Parent root = FXMLLoader.load(getClass().getResource("/ScrabbleGame.fxml"));
        primaryStage.setTitle("War Card Game in JavaFX");
        Scene scene  = new Scene(root);
        scene.getStylesheets().add("/css/game-page.css");
        primaryStage.setScene(scene);
        
        primaryStage.show();

        // Charger et définir l'icône
        Image icon = new Image(getClass().getResourceAsStream("/images/general/Scrabble-Logo.png"));
        primaryStage.getIcons().add(icon);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
