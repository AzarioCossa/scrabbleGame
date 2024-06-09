package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import scrabble.model.utils.TilePlacementException;

class GameBoardTest {

	@Test
	void testStarSquare() {
	
		GameBoard gameBoard = new GameBoard();

        assertTrue(gameBoard.getSquares()[7][7].getSquareType() == SquareType.CENTRAL);
        assertTrue(gameBoard.getSquares()[7][8].getSquareType() == SquareType.STANDARD);

		
	}

	 @Test
	    void testPlaceTileOutsideBounds() {
	        GameBoard gameBoard = new GameBoard();
	        Tile tile = new Tile(FrenchLetters.A);

	        assertThrows(TilePlacementException.class, () -> gameBoard.placeTileGameBoard(tile, -1, 0));
	        assertThrows(TilePlacementException.class, () -> gameBoard.placeTileGameBoard(tile, 0, -1));
	        assertThrows(TilePlacementException.class, () -> gameBoard.placeTileGameBoard(tile, BoardSizeConstants.BOARD_SIZE, 0));
	        assertThrows(TilePlacementException.class, () -> gameBoard.placeTileGameBoard(tile, 0, BoardSizeConstants.BOARD_SIZE));
	    }
}
