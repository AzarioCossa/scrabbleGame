package scrabble.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class JokerTileTest {

	private static final int JOKER_SCORE = 0;
	private JokerTile jokerTile;
	
	@BeforeEach
	void init() {
        jokerTile = new JokerTile();
	}
	
    @Test
    void testJokerTileInitialization() {
        assertEquals(FrenchLetters.JOCKER, jokerTile.getLetter());
        assertNull(jokerTile.getJokerLetter());
    }

    @ParameterizedTest
    @EnumSource(FrenchLetters.class)
    void testSetAndGetJokerLetter(FrenchLetters letter) {
        jokerTile.setJockerLetter(letter);
        assertEquals(letter, jokerTile.getJokerLetter());
    }
    @Test
    void testWeightZero() {
    	jokerTile.setJockerLetter(FrenchLetters.A);
    	assertEquals(JOKER_SCORE,jokerTile.getWeight());
    }
}
