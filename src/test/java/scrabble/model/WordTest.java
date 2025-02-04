package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.Map;

/**
 * The WordTest class contains unit tests for the Word class.
 */
class WordTest {

    private Word word;
    private Tile tileA;
    private Tile tileB;
    private Position position1;
    private Position position2;

    /**
     * Sets up the tiles, positions, and word before all tests.
     */
    @BeforeAll
    public void setUp() {
        tileA = new Tile(FrenchLetters.A);
        tileB = new Tile(FrenchLetters.B);
        position1 = new Position(0, 0);
        position2 = new Position(0, 1);
        word = new Word();
    }

    /**
     * Tests the addTile method of the Word class.
     */
    @Test
    void testAddTile() {
        assertTrue(word.addTile(tileA, position1));
        assertFalse(word.addTile(tileA, position1));
    }

    /**
     * Tests the getTiles method of the Word class.
     */
    @Test
    void testGetTiles() {
        word.addTile(tileA, position1);
        Map<Position, Tile> tiles = word.getTiles();
        assertEquals(1, tiles.size());
        assertTrue(tiles.containsKey(position1));
        assertEquals(tileA, tiles.get(position1));
    }

    /**
     * Tests the size method of the Word class.
     */
    @Test
    void testSize() {
        assertEquals(0, word.size());
        word.addTile(tileA, position1);
        assertEquals(1, word.size());
    }

    /**
     * Tests the containsKey method of the Word class.
     */
    @Test
    void testContainsKey() {
        assertFalse(word.containsKey(position1));
        word.addTile(tileA, position1);
        assertTrue(word.containsKey(position1));
    }

    /**
     * Tests the equals and hashCode methods of the Word class.
     */
    @Test
    void testEqualsAndHashCode() {
        Map<Position, Tile> tilesMap = new HashMap<>();
        tilesMap.put(position1, tileA);
        Word anotherWord = new Word(tilesMap);

        word.addTile(tileA, position1);

        assertEquals(word, anotherWord);
        assertEquals(word.hashCode(), anotherWord.hashCode());

        anotherWord.addTile(tileB, position2);
        assertNotEquals(word, anotherWord);
    }
}
