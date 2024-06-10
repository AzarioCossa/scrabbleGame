package scrabble.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The TileTest class contains unit tests for the Tile class.
 */
public class TileTest {

    private static final int B_WEIGHT = 3;
    private static final int A_WEIGHT = 1;
    private static Tile tileA;
    private static Tile tileB;

    /**
     * Sets up the tiles before all tests.
     */
    @BeforeAll
    public static void setUp() {
        tileA = new Tile(FrenchLetters.A);
        tileB = new Tile(FrenchLetters.B);
    }

    /**
     * Tests the initialization of a Tile object.
     */
    @Test
    void testTileInitialization() {
        assertEquals(FrenchLetters.A, tileA.getLetter());
        assertEquals(FrenchLetters.B, tileB.getLetter());
    }

    /**
     * Tests the getWeight method of the Tile class.
     */
    @Test
    void testGetWeight() {
        assertEquals(A_WEIGHT, tileA.getWeight());
        assertEquals(B_WEIGHT, tileB.getWeight());
    }
}
