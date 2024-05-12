package scrabble.model;

public class SquareStar extends Square {
	static Position position = new Position(8, 8);

	public SquareStar() {
		super('*', position);
	}

}
