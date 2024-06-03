package scrabble.model;

import java.io.Serializable;

public class Tile implements Serializable {

	private final FrenchLetters letter;
	
	
	public FrenchLetters getLetter() {
		return letter;
	}


	public Tile(FrenchLetters letter) {
		this.letter = letter;
	}

	public int getWeight() {
		return this.letter.getWeight();
	}
}
