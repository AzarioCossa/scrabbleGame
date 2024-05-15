package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameBoardTest {

	@Test
	public void testStarSquare() {
	
		GameBoard gameBoard = new GameBoard();

		assertTrue(gameBoard.getSquares()[7][7] instanceof SquareStar);
		assertTrue(gameBoard.getSquares()[1][1] instanceof Square);

		
	}

}
