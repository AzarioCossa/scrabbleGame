package scrabble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SquareTest {

	  @Test
	    public void testConstructorAndGetters() {
		  
	        Square square = new Square('.');

	        assertEquals('.', square.getSymbol());
	        assertEquals(2, square.getRow());
	        assertEquals(3, square.getColumn());
	        assertNull(square.getTile()); 
	    }

	    @Test
	    public void testPlaceTile() {
	        Square square = new Square('.');
	        Tile tile = new Tile(FrenchLetters.A);

	
	        square.placeTile(tile);

	      
	        assertEquals(tile, square.getTile());
	        assertFalse(square.isBusy()); 
	    }

	    @Test
	    public void testIsBusy() {
	    	
	        Square square1 = new Square('.');
	        Square square2 = new Square('X');
	        Tile tile = new Tile(FrenchLetters.A);


	        assertTrue(square1.isBusy());
	        assertTrue(square2.isBusy());

	        square1.placeTile(tile);


	        assertFalse(square1.isBusy());
	        assertTrue(square2.isBusy());
	    }}
