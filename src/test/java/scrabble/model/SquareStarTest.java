package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareStarTest {

	@Test
    public void testConstructor() {
        SquareStar squareStar = new SquareStar();

        // Vérifier les valeurs après la création
        assertEquals('*', squareStar.getSymbol());
        assertEquals(8, squareStar.getX());
        assertEquals(8, squareStar.getY());
        assertNull(squareStar.getTile()); // Vérifier que tile est null après la création
    }

}
