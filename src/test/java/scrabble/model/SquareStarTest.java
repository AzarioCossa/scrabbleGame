package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareStarTest {

	@Test
    public void testConstructor() {
        SquareStar squareStar = new SquareStar();

        assertEquals('*', squareStar.getSymbol());
     
        assertNull(squareStar.getTile());
    }

}
