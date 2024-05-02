package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BagTest {


	@Test
	public void testDrawTile() {
		Bag bag = new Bag();
		int initialSize = bag.getTiles().size();

		Tile drawnTile = bag.drawTile();

		assertEquals(initialSize - 1, bag.getTiles().size());
		assertFalse(bag.getTiles().contains(drawnTile));
	}

}
