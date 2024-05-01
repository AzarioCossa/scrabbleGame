package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareTest {
	  @Test
	    public void testConstructorAndGetters() {
	        Square square = new Square('.', 2, 3);

	        assertEquals('.', square.getSymbol());
	        assertEquals(2, square.getX());
	        assertEquals(3, square.getY());
	        assertNull(square.getTile()); 
	    }

	    @Test
	    public void testPlaceTile() {
	        Square square = new Square('.', 2, 3);
	        Tile tile = new Tile(Letters.A);

	
	        square.placeTile(tile);

	      
	        assertEquals(tile, square.getTile());
	        assertFalse(square.isBusy()); 
	    }

	    @Test
	    public void testIsBusy() {
	        Square square1 = new Square('.', 2, 3);
	        Square square2 = new Square('X', 4, 5);
	        Tile tile = new Tile(Letters.A);


	        assertTrue(square1.isBusy());
	        assertTrue(square2.isBusy());

	        square1.placeTile(tile);


	        assertFalse(square1.isBusy());
	        assertTrue(square2.isBusy());
	    }}
