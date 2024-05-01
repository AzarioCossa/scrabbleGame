package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {
	
	@Test
    public void testConstructorAndGetLetter() {
        Letters letter = Letters.A;
        Tile tile = new Tile(letter);

        assertEquals(letter, tile.getLetter());
    }

}
