package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {
	
	@Test
    public void testConstructorAndGetLetter() {
        FrenchLetters letter = FrenchLetters.A;
        Tile tile = new Tile(letter);

        assertEquals(letter, tile.getLetter());
    }

}
