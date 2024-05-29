package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import scrabble.model.utils.RackIsFullException;

class RackTest {

	@Test
	public void testDrawTile() throws RackIsFullException  {
		
		Rack rack = new Rack();
		Tile tileToRemove = new Tile(FrenchLetters.B);

		rack.addTile(new Tile(FrenchLetters.A));
		rack.addTile(tileToRemove);
		rack.addTile(new Tile(FrenchLetters.B));
		assertEquals(tileToRemove, rack.drawTile(tileToRemove));
		assertFalse(rack.getTiles().contains(tileToRemove));

		Tile tileNotInRack = new Tile(FrenchLetters.Z);
		assertNull(rack.drawTile(tileNotInRack));
	}

	@Test
	public void testAddTile() throws RackIsFullException {
		Rack rack = new Rack();

		Tile tileToAdd = new Tile(FrenchLetters.A);

		assertTrue(rack.addTile(tileToAdd));

		assertTrue(rack.getTiles().contains(tileToAdd));
	}

	@Test
	public void testGetTiles() throws RackIsFullException {
		ArrayList<Tile> initialTiles = new ArrayList<>();
		initialTiles.add(new Tile(FrenchLetters.A));
		initialTiles.add(new Tile(FrenchLetters.B));
		initialTiles.add(new Tile(FrenchLetters.C));

		Rack rack = new Rack();
		rack.addTile(new Tile(FrenchLetters.A));
		rack.addTile(new Tile(FrenchLetters.B));
		rack.addTile(new Tile(FrenchLetters.C));

		ArrayList<Tile> tiles = rack.getTiles();

		//assertEquals(initialTiles, tiles);
	}
}
