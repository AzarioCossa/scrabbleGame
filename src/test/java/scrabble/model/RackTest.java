package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import scrabble.model.utils.RackIsFullException;

class RackTest {

	@Test
	public void testDrawTile() {
		Tile tileToRemove = new Tile(FrenchLetters.B);

		ArrayList<Tile> initialTiles = new ArrayList<>();
		initialTiles.add(new Tile(FrenchLetters.A));
		initialTiles.add(tileToRemove);
		initialTiles.add(new Tile(FrenchLetters.B));
		Rack rack = new Rack(initialTiles);

		assertEquals(tileToRemove, rack.drawTile(tileToRemove));
		assertFalse(rack.getTiles().contains(tileToRemove));

		Tile tileNotInRack = new Tile(FrenchLetters.Z);
		assertNull(rack.drawTile(tileNotInRack));
	}

	@Test
	public void testAddTile() throws RackIsFullException {
		Rack rack = new Rack(new ArrayList<>());

		Tile tileToAdd = new Tile(FrenchLetters.A);

		assertTrue(rack.addTile(tileToAdd));

		assertTrue(rack.getTiles().contains(tileToAdd));
	}

	@Test
	public void testGetTiles() {
		ArrayList<Tile> initialTiles = new ArrayList<>();
		initialTiles.add(new Tile(FrenchLetters.A));
		initialTiles.add(new Tile(FrenchLetters.B));
		initialTiles.add(new Tile(FrenchLetters.C));

		Rack rack = new Rack(initialTiles);

		ArrayList<Tile> tiles = rack.getTiles();

		assertEquals(initialTiles, tiles);
	}
}
