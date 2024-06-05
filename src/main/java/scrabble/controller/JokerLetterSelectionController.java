package scrabble.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scrabble.model.FrenchLetters;
import scrabble.model.JokerTile;
import scrabble.util.AlertManager;

public class JokerLetterSelectionController {

    @FXML
    private TextField letterTextField;

    private JokerTile jokerTile;

    public void initialize() {
    	
    }

    public void setJokerTile(JokerTile jokerTile) {
        this.jokerTile = jokerTile;
    }

    @FXML
    private void submitLetter() {
        String letter = letterTextField.getText().trim().toUpperCase();
        if (letter.length() == 1 && Character.isLetter(letter.charAt(0))) {
            char inputLetter = letter.charAt(0);
            if (Character.isAlphabetic(inputLetter)) {
                FrenchLetters frenchLetter = FrenchLetters.getLetter(inputLetter);
                jokerTile.setJockerLetter(frenchLetter);
                closeWindow();
            } else {
                AlertManager.showJokerLetterSelectionAlert(this.jokerTile);
            }
        } else {
            AlertManager.showJokerLetterSelectionAlert(this.jokerTile);
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) letterTextField.getScene().getWindow();
        stage.close();
    }
}
