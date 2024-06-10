package scrabble.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.utils.BagIsFullException;
import scrabble.model.utils.EmptyBagException;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class BagTest {

    private static final int INITIAL_BAG_SIZE = 102;
    private Bag bag;

    @BeforeEach
    void setUp() {
        bag = new Bag();
    }

    @Test
    void testSizeOfBag() {
        int initialSize = bag.getTiles().size();
        assertEquals(INITIAL_BAG_SIZE, initialSize);
    }

    @Test
    void testDrawTileEmpty() {
        bag.clear();

        EmptyBagException thrown = Assertions.assertThrows(EmptyBagException.class, () -> {
            bag.drawTile();
        });

        assertEquals("You can't draw a tile when the bag is empty", thrown.getMessage());
    }

    @Test
    void testShuffle() {
        List<Tile> initialTiles = List.copyOf(bag.getTiles());
        bag.shuffle();
        List<Tile> shuffledTiles = bag.getTiles();
        
        assertNotEquals(initialTiles, shuffledTiles);
    }

    @Test
    void testAddTile() throws BagIsFullException, EmptyBagException {
        Tile newTile = new Tile(FrenchLetters.A);
        bag.drawTile();
        assertTrue(bag.addTile(newTile));

        bag.clear();
        for (int i = 0; i < Bag.LIMIT_BAG_CAPACITY; i++) {
            bag.addTile(newTile);
        }

        BagIsFullException thrown = Assertions.assertThrows(BagIsFullException.class, () -> {
            bag.addTile(newTile);
        });

        assertEquals("The bag is full", thrown.getMessage());
    }

    @Test
    void testIsEmpty() {
        assertFalse(bag.isEmpty());

        bag.clear();
        assertTrue(bag.isEmpty());
    }

    @Test
    void testDrawTile() throws EmptyBagException {
        int initialSize = bag.size();
        Tile drawnTile = bag.drawTile();
        assertNotNull(drawnTile);
        assertEquals(initialSize - 1, bag.size());
    }

    @Test
    void testDrawUntilEmpty() throws EmptyBagException {
        while (!bag.isEmpty()) {
            Tile drawnTile = bag.drawTile();
            assertNotNull(drawnTile);
        }

        EmptyBagException thrown = Assertions.assertThrows(EmptyBagException.class, () -> {
            bag.drawTile();
        });

        assertEquals("You can't draw a tile when the bag is empty", thrown.getMessage());
    }
}
