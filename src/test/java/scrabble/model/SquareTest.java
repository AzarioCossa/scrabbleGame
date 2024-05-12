package scrabble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquareTest {
	
	Position position;
	
	@BeforeEach
	void init() {
		position = new Position(2, 3);
	}
	
	  @Test
	    public void testConstructorAndGetters() {
		  
	        Square square = new Square('.', position);

	        assertEquals('.', square.getSymbol());
	        assertEquals(2, square.getRow());
	        assertEquals(3, square.getColumn());
	        assertNull(square.getTile()); 
	    }

	    @Test
	    public void testPlaceTile() {
	        Square square = new Square('.', position);
	        Tile tile = new Tile(Letters.A);

	
	        square.placeTile(tile);

	      
	        assertEquals(tile, square.getTile());
	        assertFalse(square.isBusy()); 
	    }

	    @Test
	    public void testIsBusy() {
	    	
	    Position position2 = new Position(4, 5);
	    	
	        Square square1 = new Square('.', position);
	        Square square2 = new Square('X', position2);
	        Tile tile = new Tile(Letters.A);


	        assertTrue(square1.isBusy());
	        assertTrue(square2.isBusy());

	        square1.placeTile(tile);


	        assertFalse(square1.isBusy());
	        assertTrue(square2.isBusy());
	    }}
