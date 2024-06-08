package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameBoardTest {

	@Test
	void testStarSquare() {
	
		GameBoard gameBoard = new GameBoard();

		assertTrue(gameBoard.getSquares()[1][1] instanceof Square);

		
	}

}
