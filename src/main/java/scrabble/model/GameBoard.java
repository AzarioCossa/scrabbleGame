package scrabble.model;

import java.util.ArrayList;


public class GameBoard {
	
	private ArrayList<Square> squares;

    public GameBoard(int x, int y) {
        squares = new ArrayList<>();

        squares.add(new SquareStar()); 
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                    squares.add(new Square(i, j));
            }
        }
    }

	public ArrayList<Square> getSquares() {
		return squares;
	}

}
