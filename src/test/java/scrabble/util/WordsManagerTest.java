package scrabble.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.BoardSizeConstants;
import scrabble.model.FrenchLetters;
import scrabble.model.GameBoard;
import scrabble.model.Position;
import scrabble.model.Tile;
import scrabble.model.Word;
import scrabble.model.utils.TilePlacementException;

class WordsManagerTest {

	private GameBoard gameBoard;
	private WordsManager wordsManager;

	@BeforeEach
	public void setUp() {
		// Initialize an empty game board for testing
		gameBoard = new GameBoard();
		wordsManager = new WordsManager(gameBoard);
	}

	@Test
	public void testValidateWords_FirstMoveValid() {
		// Simulate the first move passing through the center
		Map<Position, Tile> tiles = new HashMap<>();
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX),
				new Tile(FrenchLetters.H));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX + 1),
				new Tile(FrenchLetters.E));

		assertTrue(wordsManager.validateWords(tiles));
	}

	@Test
	public void testValidateWords_FirstMoveInvalid() {
		// Simulate the first move not passing through the center
		Map<Position, Tile> tiles = new HashMap<>();
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX + 1),
				new Tile(FrenchLetters.H));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX + 2),
				new Tile(FrenchLetters.E));

		assertFalse(wordsManager.validateWords(tiles));
	}

	@Test
	void testValidateWords_HorizontalWordValid() throws TilePlacementException {
		// Place some tiles on the board to simulate a non-empty board
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.T), BoardSizeConstants.MIDDLE_INDEX,
				BoardSizeConstants.MIDDLE_INDEX);

		// Simulate placing a valid horizontal word
		Map<Position, Tile> tiles = new HashMap<>();
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX + 1),
				new Tile(FrenchLetters.E));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX + 2),
				new Tile(FrenchLetters.S));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX + 3),
				new Tile(FrenchLetters.T));

		assertTrue(wordsManager.validateWords(tiles));
	}

	@Test
	void testValidateWords_VerticalWordValid() throws TilePlacementException {
		// Place some tiles on the board to simulate a non-empty board
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.T), BoardSizeConstants.MIDDLE_INDEX,
				BoardSizeConstants.MIDDLE_INDEX);

		// Simulate placing a valid vertical word
		Map<Position, Tile> tiles = new HashMap<>();
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX + 1, BoardSizeConstants.MIDDLE_INDEX),
				new Tile(FrenchLetters.E));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX + 2, BoardSizeConstants.MIDDLE_INDEX),
				new Tile(FrenchLetters.S));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX + 3, BoardSizeConstants.MIDDLE_INDEX),
				new Tile(FrenchLetters.T));

		assertTrue(wordsManager.validateWords(tiles));
	}

	@Test
	void testValidateWords_DiscontinuousWordInvalid() {
		Map<Position, Tile> tiles = new HashMap<>();
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX),
				new Tile(FrenchLetters.H));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX + 2),
				new Tile(FrenchLetters.E));

		assertFalse(wordsManager.validateWords(tiles));
	}

	@Test
	void testCalculateWordScore() {

		GameBoard gameBoard = new GameBoard();
		WordsManager wordsManager = new WordsManager(gameBoard);

		Map<Position, Tile> tiles = new HashMap<>();
		tiles.put(new Position(8, 8), new Tile(FrenchLetters.A));
		tiles.put(new Position(8, 9), new Tile(FrenchLetters.B));
		tiles.put(new Position(8, 10), new Tile(FrenchLetters.C));

		assertEquals(7, wordsManager.calculateWordScore(new Word(tiles)));
	}

}
