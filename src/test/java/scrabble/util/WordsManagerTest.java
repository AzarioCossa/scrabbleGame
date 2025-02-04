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
		gameBoard = new GameBoard();
		wordsManager = new WordsManager(gameBoard);
	}

	@Test
	void testValidateWords_FirstMoveValid() {
		Map<Position, Tile> tiles = new HashMap<>();
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX),
				new Tile(FrenchLetters.H));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX + 1),
				new Tile(FrenchLetters.E));

		assertTrue(wordsManager.validateWords(tiles));
	}

	@Test
	void testValidateWords_FirstMoveInvalid() {
		Map<Position, Tile> tiles = new HashMap<>();
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX + 1),
				new Tile(FrenchLetters.H));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX + 2),
				new Tile(FrenchLetters.E));

		assertFalse(wordsManager.validateWords(tiles));
	}

	@Test
	void testValidateWords_HorizontalWordValid() throws TilePlacementException {
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.T), BoardSizeConstants.MIDDLE_INDEX,
				BoardSizeConstants.MIDDLE_INDEX);

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
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.T), BoardSizeConstants.MIDDLE_INDEX,
				BoardSizeConstants.MIDDLE_INDEX);

		Map<Position, Tile> tiles = new HashMap<>();
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX + 1, BoardSizeConstants.MIDDLE_INDEX),
				new Tile(FrenchLetters.E));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX + 2, BoardSizeConstants.MIDDLE_INDEX),
				new Tile(FrenchLetters.S));
		tiles.put(new Position(BoardSizeConstants.MIDDLE_INDEX + 3, BoardSizeConstants.MIDDLE_INDEX),
				new Tile(FrenchLetters.T));

		assertTrue(wordsManager.validateWords(tiles));
	}
	/*
	 * @Test void testValidateWords_DiscontinuousWordInvalid() { Map<Position, Tile>
	 * tiles = new HashMap<>(); tiles.put(new
	 * Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX),
	 * new Tile(FrenchLetters.H)); tiles.put(new
	 * Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX +
	 * 2), new Tile(FrenchLetters.E));
	 * 
	 * assertFalse(wordsManager.validateWords(tiles)); }
	 */

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

	@Test
	void testCalculateWordScoreWithJoker() {

		GameBoard gameBoard = new GameBoard();
		WordsManager wordsManager = new WordsManager(gameBoard);

		Map<Position, Tile> tiles = new HashMap<>();
		tiles.put(new Position(8, 8), new Tile(FrenchLetters.A));
		tiles.put(new Position(8, 9), new Tile(FrenchLetters.JOCKER));

		assertEquals(1, wordsManager.calculateWordScore(new Word(tiles)));
	}

	
	@Test
	void testCalculateWordScoreMultipleWords() throws TilePlacementException {
		// Horizontal word "BEAR"
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.B), 8, 8);
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.E), 8, 9);
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.A), 8, 10);
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.R), 8, 11);
		 // Vertical word "BAT"
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.T), 9, 10);
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.A), 10, 10);
		gameBoard.placeTileGameBoard(new Tile(FrenchLetters.B), 11, 10);

		 // New word "TREK" formed horizontally
		Map<Position, Tile> newWordTiles = new HashMap<>();
		newWordTiles.put(new Position(9, 8), new Tile(FrenchLetters.T));
		newWordTiles.put(new Position(9, 9), new Tile(FrenchLetters.R));
		newWordTiles.put(new Position(9, 10), new Tile(FrenchLetters.E));
		newWordTiles.put(new Position(9, 11), new Tile(FrenchLetters.K));

		assertEquals(21, wordsManager.calculateMoveScore(newWordTiles));
	}

}
