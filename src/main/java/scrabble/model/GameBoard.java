package scrabble.model;

public class GameBoard {

	private Square[][] squares;
	private int sizeX;
	private int sizeY;

	public GameBoard(int x, int y) {
		this.sizeX = x;
		this.sizeY = y;

		squares = new Square[x][y];

		for (int row = 0; row < x; row++) {
			for (int column = 0; column < y; column++) {
				squares[row][column] = new Square();
			}
		}
		squares[7][7] = new SquareStar();
	}

	public Square[][] getSquares() {
		return squares;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}
	
}
