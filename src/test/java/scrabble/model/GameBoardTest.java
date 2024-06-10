package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import scrabble.model.utils.TilePlacementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

class GameBoardTest {

    private GameBoard gameBoard;
    private Square[][] squares;

    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard();
        squares = gameBoard.getSquares();
    }

    @Test
    void testBoardInitialization() {
        assertNotNull(squares);
        assertEquals(BoardSizeConstants.BOARD_SIZE, squares.length);

        for (int row = 0; row < BoardSizeConstants.BOARD_SIZE; row++) {
            for (int col = 0; col < BoardSizeConstants.BOARD_SIZE; col++) {
                assertNotNull(squares[row][col], "Each square should be initialized");
                String expectedBonusType = BoardSizeConstants.boardLayout[row][col];
                SquareType expectedSquareType;
                switch (expectedBonusType) {
                    case "C":
                        expectedSquareType = SquareType.CENTRAL;
                        break;
                    case "TW":
                        expectedSquareType = SquareType.TRIPLE_WORD;
                        break;
                    case "DW":
                        expectedSquareType = SquareType.DOUBLE_WORD;
                        break;
                    case "TL":
                        expectedSquareType = SquareType.TRIPLE_LETTER;
                        break;
                    case "DL":
                        expectedSquareType = SquareType.DOUBLE_LETTER;
                        break;
                    default:
                        expectedSquareType = SquareType.STANDARD;
                        break;
                }
                assertEquals(expectedSquareType, squares[row][col].getSquareType());
            }
        }
    }

    @Test
    void testIsEmpty() {
        assertTrue(gameBoard.isEmpty());

        Tile tile = new Tile(FrenchLetters.A);
        gameBoard.getSquares()[0][0].placeTileSquare(tile);

        assertFalse(gameBoard.isEmpty());
    }

    @Test
    void testIsEmptyWithPosition() {
        Position pos = new Position(1, 1);
        assertTrue(gameBoard.isEmpty(pos));

        Tile tile = new Tile(FrenchLetters.A);
        gameBoard.getSquares()[0][0].placeTileSquare(tile);

        assertFalse(gameBoard.isEmpty(pos));
    }

    @Test
    void testIsNotEmptyWithPosition() {
        Position pos = new Position(1, 1);
        assertFalse(gameBoard.isNotEmpty(pos));

        Tile tile = new Tile(FrenchLetters.A);
        gameBoard.getSquares()[0][0].placeTileSquare(tile);

        assertTrue(gameBoard.isNotEmpty(pos));
    }

    @Test
    void testPlaceTileGameBoard() throws TilePlacementException {
        Tile tile = new Tile(FrenchLetters.A);
        gameBoard.placeTileGameBoard(tile, 0, 0);
        assertEquals(tile, gameBoard.getSquares()[0][0].getTile());

        TilePlacementException thrown = Assertions.assertThrows(TilePlacementException.class, () -> {
            gameBoard.placeTileGameBoard(tile, -1, 0);
        });
        assertEquals("Row or column index is out of bounds.", thrown.getMessage());

        thrown = Assertions.assertThrows(TilePlacementException.class, () -> {
            gameBoard.placeTileGameBoard(tile, 0, 15);
        });
        assertEquals("Row or column index is out of bounds.", thrown.getMessage());
    }
}
