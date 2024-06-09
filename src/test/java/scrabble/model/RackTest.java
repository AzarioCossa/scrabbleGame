package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import scrabble.model.utils.RackIsFullException;

class RackTest {

    @Test
    void testDrawTile() throws RackIsFullException {
        Rack rack = new Rack();
        Tile tileToRemove = new Tile(FrenchLetters.B);

        rack.addTile(new Tile(FrenchLetters.A));
        rack.addTile(tileToRemove);
        rack.addTile(new Tile(FrenchLetters.C));

        assertEquals(tileToRemove, rack.drawTile(tileToRemove));
        assertFalse(rack.getTiles().contains(tileToRemove));

        Tile tileNotInRack = new Tile(FrenchLetters.Z);
        assertNull(rack.drawTile(tileNotInRack));
    }

    @Test
     void testAddTile() throws RackIsFullException {
        Rack rack = new Rack();
        Tile tileToAdd = new Tile(FrenchLetters.A);

        assertTrue(rack.addTile(tileToAdd));
        assertTrue(rack.getTiles().contains(tileToAdd));
    }

    @Test
     void testGetTiles() throws RackIsFullException {
        Rack rack = new Rack();
        rack.addTile(new Tile(FrenchLetters.A));
        rack.addTile(new Tile(FrenchLetters.B));
        rack.addTile(new Tile(FrenchLetters.C));

        List<Tile> tiles = rack.getTiles();

        // Ensure that the returned list matches the expected tiles
        assertEquals(3, tiles.size());
        assertTrue(tiles.contains(new Tile(FrenchLetters.A)));
        assertTrue(tiles.contains(new Tile(FrenchLetters.B)));
        assertTrue(tiles.contains(new Tile(FrenchLetters.C)));
    }
}
