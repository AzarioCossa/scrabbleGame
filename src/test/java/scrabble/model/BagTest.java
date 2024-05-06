package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import scrabble.model.utils.EmptyBagException;

class BagTest {

	@Test
	public void testDrawTile() {
	    Bag bag = new Bag();
	    int initialSize = bag.getTiles().size();
	    
	    assertTrue(initialSize > 0);
	    
	    Tile drawnTile = null;

	    try {
	        drawnTile = bag.drawTile();
	    } catch (EmptyBagException e) {
	        fail("The bag should not be empty when drawing a tile.");
	    }

	    assertEquals(initialSize - 1, bag.getTiles().size()); 
	    assertFalse(bag.getTiles().contains(drawnTile));
	}

}
