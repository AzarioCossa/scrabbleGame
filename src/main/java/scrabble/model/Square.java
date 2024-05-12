package scrabble.model;

public class Square {

	protected char symbol;
	protected Position position;
	protected Tile tile;

	protected Square(char symbol, Position position) {
		this.tile = null;
		this.symbol = symbol;
		this.position = position;
	}

	public Square(Position position) {
		this('.', position);
	}

	public char getSymbol() {
		return symbol;
	}

	public int getColumn() {
		return this.position.column();
	}

	public int getRow() {
		return this.position.row();
	}

	public Tile getTile() {
		return tile;
	}

	public void placeTile(Tile tile) {
		this.tile = tile;
	}

	public Boolean isBusy() {
		return this.tile == null;
	}
	

}
