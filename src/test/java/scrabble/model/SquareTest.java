package scrabble.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The SquareTest class contains unit tests for the Square class.
 */
class SquareTest {

    private Square standardSquare;
    private Square doubleWordSquare;
    private Tile tileA;
    private Tile tileB;

    /**
     * Sets up the Square objects and tiles before each test.
     */
    @BeforeEach
    public void setUp() {
        standardSquare = new Square(SquareType.STANDARD);
        doubleWordSquare = new Square(SquareType.DOUBLE_WORD);
        tileA = new Tile(FrenchLetters.A);
        tileB = new Tile(FrenchLetters.B);
    }

    /**
     * Tests the initialization of a Square object.
     */
    @Test
    void testSquareInitialization() {
        assertEquals(SquareType.STANDARD, standardSquare.getSquareType());
        assertNull(standardSquare.getTile());
    }

    /**
     * Tests the placeTileSquare method of the Square class.
     */
    @Test
    void testPlaceTileSquare() {
        standardSquare.placeTileSquare(tileA);
        doubleWordSquare.placeTileSquare(tileB);

        assertEquals(tileB, doubleWordSquare.getTile());
        assertEquals(tileA, standardSquare.getTile());
    }

    /**
     * Tests the isEmpty method of the Square class.
     */
    @Test
    void testIsEmpty() {
        assertTrue(standardSquare.isEmpty());
        standardSquare.placeTileSquare(tileA);
        assertFalse(standardSquare.isEmpty());
    }
}
