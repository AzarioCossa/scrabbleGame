package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import scrabble.model.utils.RackIsFullException;

/**
 * The Rack class represents a player's tile rack in the Scrabble game.
 * It provides methods to add, remove, shuffle, and manipulate tiles on the rack.
 */
public class Rack {

    /**
     * The list of tiles in the rack.
     */
    private ArrayList<Tile> tiles;

    /**
     * The maximum capacity of the rack.
     */
    public static final Integer LIMIT_RACK_CAPACITY = 7;

    /**
     * Constructs a new Rack with an empty list of tiles.
     */
    public Rack() {
        tiles = new ArrayList<>();
    }

    /**
     * Returns the list of tiles currently in the rack.
     * 
     * @return the list of tiles
     */
    public List<Tile> getTiles() {
        return tiles;
    }

    /**
     * Shuffles the tiles in the rack.
     */
    public void shuffle() {
        Collections.shuffle(tiles);
    }

    /**
     * Adds a tile to the rack if there is space available.
     * Throws RackIsFullException if the rack is already full.
     * 
     * @param tile the tile to add
     * @return true if the tile was added successfully
     * @throws RackIsFullException if the rack is full
     */
    public Boolean addTile(Tile tile) throws RackIsFullException {
        if (this.tiles.size() >= LIMIT_RACK_CAPACITY) {
            throw new RackIsFullException("Le rack est plein !");
        }
        return this.tiles.add(tile);
    }

    /**
     * Swaps the positions of two tiles in the rack.
     * 
     * @param tile1 the first tile to swap
     * @param tile2 the second tile to swap
     */
    public void swapTiles(Tile tile1, Tile tile2) {
        int index1 = tiles.indexOf(tile1);
        int index2 = tiles.indexOf(tile2);
        if (index1 != -1 && index2 != -1) {
            Collections.swap(tiles, index1, index2);
        }
    }

    /**
     * Draws (removes) a specific tile from the rack.
     * 
     * @param tile the tile to draw
     * @return the drawn tile if it was in the rack, null otherwise
     */
    public Tile drawTile(Tile tile) {
        if (tiles.contains(tile)) {
            tiles.remove(tile);
            return tile;
        } else {
            return null;
        }
    }

    /**
     * Replaces the first joker tile in the rack with a new tile.
     * 
     * @param newTile the new tile to replace the joker with
     */
    public void replaceJoker(Tile newTile) {
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if (tile.getLetter() == FrenchLetters.JOCKER) {
                tiles.set(i, newTile);
                break;
            }
        }
    }

    /**
     * Removes a specific tile from the rack.
     * 
     * @param tile the tile to remove
     * @return true if the tile was removed successfully, false otherwise
     */
    public Boolean removeTile(Tile tile) {
        return this.tiles.remove(tile);
    }

    /**
     * Returns the hash code value for this rack.
     * The hash code is based on the list of tiles.
     * 
     * @return the hash code value for this rack
     */
    @Override
    public int hashCode() {
        return Objects.hash(tiles);
    }

    /**
     * Compares this rack to the specified object.
     * The result is true if and only if the argument is not null and is a Rack object that has the same tiles as this object.
     * 
     * @param obj the object to compare this Rack against
     * @return true if the given object represents a Rack equivalent to this rack, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rack other = (Rack) obj;
        return Objects.equals(tiles, other.tiles);
    }
}
