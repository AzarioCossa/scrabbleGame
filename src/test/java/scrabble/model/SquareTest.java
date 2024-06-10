package scrabble.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    private Square standardSquare;
    private Square doubleWordSquare;
    private Tile tileA;
    private  Tile tileB;

    @BeforeEach
    public void setUp() {
        standardSquare = new Square(SquareType.STANDARD);
        doubleWordSquare = new Square(SquareType.DOUBLE_WORD);
        tileA = new Tile(FrenchLetters.A);
        tileB = new Tile(FrenchLetters.B);
    }

    @Test
    void testSquareInitialization() {
        assertEquals(SquareType.STANDARD, standardSquare.getSquareType());
        assertNull(standardSquare.getTile());
    }

    @Test
    void testPlaceTileSquare() {
  
        standardSquare.placeTileSquare(tileA);
        doubleWordSquare.placeTileSquare(tileB);
        
        assertEquals(tileB, doubleWordSquare.getTile());
        assertEquals(tileA, standardSquare.getTile());

    }

    @Test
    void testIsEmpty() {
        assertTrue(standardSquare.isEmpty());
        standardSquare.placeTileSquare(tileA);
        assertFalse(standardSquare.isEmpty());
    }
}
