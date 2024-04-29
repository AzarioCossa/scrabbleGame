package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameBoardTest {

	@Test
	public void testStarSquare() {
		int x = 10;
		int y = 10;
		GameBoard gameBoard = new GameBoard(x, y);

		assertTrue(gameBoard.getSquares()[8][8] instanceof SquareStar);
		
	}

}
