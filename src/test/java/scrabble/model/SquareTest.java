package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareTest {
	  @Test
	    public void testConstructorAndGetters() {
	        Square square = new Square('.', 2, 3);

	        // Vérifier les valeurs après la création
	        assertEquals('.', square.getSymbol());
	        assertEquals(2, square.getX());
	        assertEquals(3, square.getY());
	        assertNull(square.getTile()); // Vérifier que tile est null après la création
	    }

	    @Test
	    public void testPlaceTile() {
	        Square square = new Square('.', 2, 3);
	        Tile tile = new Tile(Letters.A);

	        // Placer une tuile sur le carré
	        square.placeTile(tile);

	        // Vérifier que la tuile est placée correctement
	        assertEquals(tile, square.getTile());
	        assertFalse(square.isBusy()); // Vérifier que le carré n'est plus vide après avoir placé la tuile
	    }

	    @Test
	    public void testIsBusy() {
	        Square square1 = new Square('.', 2, 3);
	        Square square2 = new Square('X', 4, 5);
	        Tile tile = new Tile(Letters.A);

	        // Vérifier que les carrés sont initialement vides
	        assertTrue(square1.isBusy());
	        assertTrue(square2.isBusy());

	        // Placer une tuile sur square1
	        square1.placeTile(tile);

	        // Vérifier que square1 n'est plus vide et square2 est toujours vide
	        assertFalse(square1.isBusy());
	        assertTrue(square2.isBusy());
	    }}
