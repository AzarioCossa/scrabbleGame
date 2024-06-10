package scrabble.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The Word class represents a word formed on the Scrabble game board.
 * It consists of a collection of tiles placed on specific positions.
 */
public class Word {
    
    /**
     * A map representing the tiles of the word, keyed by their positions.
     */
    private Map<Position, Tile> tiles;

    /**
     * Constructs a new Word with the specified tiles.
     * 
     * @param tiles the tiles of the word, keyed by their positions
     */
    public Word(Map<Position, Tile> tiles) {
        this.tiles = tiles != null ? tiles : new HashMap<>();
    }

    /**
     * Constructs a new Word with an empty set of tiles.
     */
    public Word() {
        this(null);
    }

    /**
     * Adds a tile to the word at the specified position.
     * 
     * @param tile the tile to add
     * @param position the position at which to add the tile
     * @return true if the tile was added successfully, false if a tile already exists at the specified position
     */
    public Boolean addTile(Tile tile, Position position) {
        if (tiles.containsKey(position)) {
            return false;
        }
        tiles.put(position, tile);
        return true;
    }

    /**
     * Returns the tiles of the word, keyed by their positions.
     * 
     * @return the tiles of the word
     */
    public Map<Position, Tile> getTiles() {
        return tiles;
    }
    
    /**
     * Returns the number of tiles in the word.
     * 
     * @return the number of tiles in the word
     */
    public int size() {
        return tiles.size();
    }
    
    /**
     * Checks if the word contains a tile at the specified position.
     * 
     * @param pos the position to check
     * @return true if the word contains a tile at the specified position, false otherwise
     */
    public Boolean containsKey(Position pos) {
        return tiles.containsKey(pos);
    }
    
    /**
     * Returns a string representation of the word.
     * The string includes the positions and letters of all tiles in the word.
     * 
     * @return a string representation of the word
     */
    @Override
    public String toString() {
        return tiles.entrySet().stream()
            .map(entry -> "Position: " + entry.getKey().row() + ", " + entry.getKey().column() + " Letter: " + entry.getValue().getLetter())
            .collect(Collectors.joining(", "));
    }

    /**
     * Returns the hash code value for this word.
     * The hash code is based on the tiles of the word.
     * 
     * @return the hash code value for this word
     */
    @Override
    public int hashCode() {
        return Objects.hash(tiles);
    }

    /**
     * Compares this word to the specified object.
     * The result is true if and only if the argument is not null and is a Word object that has the same tiles as this object.
     * 
     * @param obj the object to compare this word against
     * @return true if the given object represents a Word equivalent to this word, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        return Objects.equals(tiles, other.tiles);
    }
}
