package scrabble.model;

public class Tile {

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
