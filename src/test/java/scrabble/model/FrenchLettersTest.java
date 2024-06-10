package scrabble.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The FrenchLettersTest class contains unit tests for the FrenchLetters enum.
 */
class FrenchLettersTest {

    private static final int JOCKER_WEIGHT = 0;
    private static final int A_WEIGHT = 1;
    private static final int JOCKER_QUANTITY = 2;
    private static final int A_QUANTITY = 9;

    /**
     * Tests the getQuantity() method of the FrenchLetters enum.
     */
    @Test
    void testGetQuantity() {
        assertEquals(A_QUANTITY, FrenchLetters.A.getQuantity());
        assertEquals(JOCKER_QUANTITY, FrenchLetters.JOCKER.getQuantity());
    }

    /**
     * Tests the getWeight() method of the FrenchLetters enum.
     */
    @Test
    void testGetWeight() {
        assertEquals(A_WEIGHT, FrenchLetters.A.getWeight());
        assertEquals(JOCKER_WEIGHT, FrenchLetters.JOCKER.getWeight());
    }

    /**
     * Tests the getLetter() method of the FrenchLetters enum.
     */
    @Test
    void testGetLetter() {
        assertEquals(FrenchLetters.A, FrenchLetters.getLetter('A'));
        assertEquals(FrenchLetters.B, FrenchLetters.getLetter('B'));
    }
}
