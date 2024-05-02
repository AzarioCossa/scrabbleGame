package scrabble.model;

public class Square {
	
	protected char symbol;
	protected int x;
	protected int y;
	protected Tile tile;
	
	protected Square(char symbol, int x, int y) {
		this.tile = null;
		this.symbol = symbol;
		this.x = x;
		this.y = y;
	}
	
	public Square(int x, int y) {
		this('.', x, y);
	}
	
	public char getSymbol() {
		return symbol;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
