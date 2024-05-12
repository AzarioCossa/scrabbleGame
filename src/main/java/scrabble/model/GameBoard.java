package scrabble.model;

public class GameBoard {

	private Square[][] squares;
	private int sizeX;
	private int sizeY;

	public GameBoard(int x, int y) {
		this.sizeX = x;
		this.sizeY = y;
		Position position = new Position(0, 0);
		squares = new Square[x][y];

		for (int row = 0; row < x; row++) {
			position.setRow(row+1);
			
			for (int column = 0; column < y; column++) {
				position.setColumn(column+1);
				squares[row][column] = new Square(position);
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
