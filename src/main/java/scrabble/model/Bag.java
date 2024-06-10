package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import scrabble.model.utils.BagIsFullException;
import scrabble.model.utils.EmptyBagException;

/**
 * The Bag class represents a bag of tiles in the Scrabble game.
 * It handles the generation, shuffling, drawing, and adding of tiles.
 */
public class Bag {
    private ArrayList<Tile> tiles;
    public static final Integer LIMIT_BAG_CAPACITY = 102;

    /**
     * Constructs a new Bag and generates the initial set of tiles.
     */
    public Bag() {
        tiles = new ArrayList<>();
        generateTiles();
    }

    /**
     * Shuffles the tiles in the bag.
     */
    public void shuffle() {
        Collections.shuffle(tiles);
    }

    /**
     * Returns an unmodifiable view of the tiles in the bag.
     * 
     * @return an unmodifiable list of tiles in the bag
     */
    public List<Tile> getTiles() {
        return tiles;
    }

    /**
     * Draws a tile from the bag. If the bag is empty, throws an EmptyBagException.
     * 
     * @return the drawn tile
     * @throws EmptyBagException if the bag is empty
     */
    public Tile drawTile() throws EmptyBagException {
        if (!tiles.isEmpty()) {
            return tiles.remove(0);
        }
        throw new EmptyBagException("You can't draw a tile when the bag is empty");
    }

    /**
     * Returns the number of tiles currently in the bag.
     * 
     * @return the number of tiles in the bag
     */
    public int size() {
        return tiles.size();
    }

    /**
     * Adds a tile to the bag. If the bag is full, throws a BagIsFullException.
     * 
     * @param tile the tile to be added
     * @return true if the tile was successfully added, false otherwise
     * @throws BagIsFullException if the bag is full
     */
    public Boolean addTile(Tile tile) throws BagIsFullException {
        if (this.tiles.size() >= LIMIT_BAG_CAPACITY) {
            throw new BagIsFullException("The bag is full");
        }
        return this.tiles.add(tile);
    }

    /**
     * Checks if the bag is empty.
     * 
     * @return true if the bag is empty, false otherwise
     */
    public Boolean isEmpty() {
        return this.tiles.isEmpty();
    }

    /**
     * Generates the initial set of tiles for the bag based on the FrenchLetters enumeration.
     * This method is called during the construction of the Bag.
     */
    private void generateTiles() {
        for (FrenchLetters letter : FrenchLetters.values()) {
            for (int i = 0; i < letter.getQuantity(); i++) {
                Tile tile;
                if (letter == FrenchLetters.JOCKER) {
                    tile = new JokerTile();
                } else {
                    tile = new Tile(letter);
                }
                tiles.add(tile);
            }
        }
        shuffle();
    }
    
   public void clear() {
    	tiles.clear();
    }
    
}
