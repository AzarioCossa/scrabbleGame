package scrabble.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scrabble.model.FrenchLetters;
import scrabble.model.JokerTile;

public class JokerLetterSelectionController {

    @FXML
    private TextField letterTextField;
    @FXML 
    private Label lblErrrLettre;

	private JokerTile jokerTile ;

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
                this.jokerTile.setJockerLetter(frenchLetter);
                
                closeWindow();
            }else {
                lblErrrLettre.setText("Vous devez saisir un caractere");
            }
        }
        else {
            lblErrrLettre.setText("Vous devez saisir un caractere");
        }
    }

    public JokerTile jokerTile() {
		return this.jokerTile;
	}
    private void closeWindow() {
        Stage stage = (Stage) letterTextField.getScene().getWindow();
        stage.close();
    }

    
}
