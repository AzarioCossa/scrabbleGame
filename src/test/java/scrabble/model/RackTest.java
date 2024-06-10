package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import scrabble.model.utils.RackIsFullException;

import java.util.List;

/**
 * The RackTest class contains unit tests for the Rack class.
 */
class RackTest {

    /**
     * Tests the drawTile method of the Rack class.
     *
     * @throws RackIsFullException if the rack is full
     */
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

    /**
     * Tests the addTile method of the Rack class.
     *
     * @throws RackIsFullException if the rack is full
     */
    @Test
    void testAddTile() throws RackIsFullException {
        Rack rack = new Rack();
        Tile tileToAdd = new Tile(FrenchLetters.A);

        assertTrue(rack.addTile(tileToAdd));
        assertTrue(rack.getTiles().contains(tileToAdd));
    }

    /**
     * Tests the getTiles method of the Rack class.
     *
     * @throws RackIsFullException if the rack is full
     */
    @Test
    void testGetTiles() throws RackIsFullException {
        Rack rack = new Rack();
        rack.addTile(new Tile(FrenchLetters.A));
        rack.addTile(new Tile(FrenchLetters.B));
        rack.addTile(new Tile(FrenchLetters.C));

        List<Tile> tiles = rack.getTiles();

        assertEquals(3, tiles.size());
        assertTrue(tiles.contains(new Tile(FrenchLetters.A)));
        assertTrue(tiles.contains(new Tile(FrenchLetters.B)));
        assertTrue(tiles.contains(new Tile(FrenchLetters.C)));
    }
}
