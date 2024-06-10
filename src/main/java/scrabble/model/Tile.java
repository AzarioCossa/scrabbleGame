package scrabble.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Tile class represents a tile in the Scrabble game.
 * It holds information about the letter on the tile.
 */
public class Tile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The letter on the tile.
     */
    private final FrenchLetters letter;

    /**
     * Constructs a new Tile with the specified letter.
     * 
     * @param letter the letter on the tile
     */
    public Tile(FrenchLetters letter) {
        this.letter = letter;
    }

    /**
     * Returns the letter on this tile.
     * 
     * @return the letter on the tile
     */
    public FrenchLetters getLetter() {
        return letter;
    }

    /**
     * Returns the weight of the letter on this tile.
     * 
     * @return the weight of the letter
     */
    public int getWeight() {
        return this.letter.getWeight();
    }

    /**
     * Returns the hash code value for this tile.
     * The hash code is based on the letter.
     * 
     * @return the hash code value for this tile
     */
    @Override
    public int hashCode() {
        return Objects.hash(letter);
    }

    /**
     * Compares this tile to the specified object.
     * The result is true if and only if the argument is not null and is a Tile object that has the same letter as this object.
     * 
     * @param obj the object to compare this tile against
     * @return true if the given object represents a Tile equivalent to this tile, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        return letter == other.letter;
    }
}
