package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import scrabble.model.utils.EmptyBagException;

class BagTest {


	private static final int INITIAL_BAG_SIZE = 102;
	
	@Test
	public void testSizeOfBag() throws EmptyBagException {
	    Bag bag = new Bag();
	    int initialSize = bag.getTiles().size();
	    
	    assertEquals(initialSize, INITIAL_BAG_SIZE);
	    
	}
	@Test
    public void testDrawTileEmpty()  {
		Bag bag  = new Bag();

		bag.getTiles().clear();
		
    	EmptyBagException thrown = Assertions.assertThrows(EmptyBagException.class, () -> {
    		
    		bag.drawTile();
    		
    	});
    	
    	Assertions.assertEquals("You can't draw a tile when the bag is empty", thrown.getMessage());
    }
	

	

}
