package scrabble.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * The JokerTileTest class contains unit tests for the JokerTile class.
 */
class JokerTileTest {

    private static final int JOKER_SCORE = 0;
    private JokerTile jokerTile;

    /**
     * Initializes a JokerTile object before each test.
     */
    @BeforeEach
    void init() {
        jokerTile = new JokerTile();
    }

    /**
     * Tests the initialization of a JokerTile object.
     */
    @Test
    void testJokerTileInitialization() {
        assertEquals(FrenchLetters.JOCKER, jokerTile.getLetter());
        assertNull(jokerTile.getJokerLetter());
    }

    /**
     * Tests the setting and getting of the joker letter for a JokerTile object.
     */
    @ParameterizedTest
    @EnumSource(FrenchLetters.class)
    void testSetAndGetJokerLetter(FrenchLetters letter) {
        jokerTile.setJockerLetter(letter);
        assertEquals(letter, jokerTile.getJokerLetter());
    }

    /**
     * Tests that the weight of a JokerTile is zero.
     */
    @Test
    void testWeightZero() {
        jokerTile.setJockerLetter(FrenchLetters.A);
        assertEquals(JOKER_SCORE, jokerTile.getWeight());
    }
}
