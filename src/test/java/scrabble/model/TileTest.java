package scrabble.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    private static final int B_WEIGHT = 3;
	private static final int A_WEIGHT = 1;
	private static Tile tileA;
    private static Tile tileB;

    @BeforeAll
    public static void setUp() {
        tileA = new Tile(FrenchLetters.A);
        tileB = new Tile(FrenchLetters.B);
    }

    @Test
    void testTileInitialization() {
        assertEquals(FrenchLetters.A, tileA.getLetter());
        assertEquals(FrenchLetters.B, tileB.getLetter());
    }

    @Test
    void testGetWeight() {
        assertEquals(A_WEIGHT, tileA.getWeight());
        assertEquals(B_WEIGHT, tileB.getWeight());
    }
  
}
