package scrabble.model;

import java.io.Serializable;
import java.util.Objects;

public class Tile implements Serializable {

	private static final long serialVersionUID = 1L;
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


	@Override
	public int hashCode() {
		return Objects.hash(letter);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		return letter == other.letter;
	}
	
}
