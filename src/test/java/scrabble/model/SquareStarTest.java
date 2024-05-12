package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareStarTest {

	@Test
    public void testConstructor() {
        SquareStar squareStar = new SquareStar();

        assertEquals('*', squareStar.getSymbol());
        assertEquals(8, squareStar.getRow());
        assertEquals(8, squareStar.getColumn());
        assertNull(squareStar.getTile());
    }

}
