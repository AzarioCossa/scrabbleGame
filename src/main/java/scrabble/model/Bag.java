package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;

import scrabble.model.utils.BagIsFullException;
import scrabble.model.utils.EmptyBagException;
import scrabble.model.utils.RackIsFullException;

public class Bag {
    private ArrayList<Tile> tiles;
    public final static Integer LIMIT_BAG_CAPACITY = 102;
    
    public Bag() {
        tiles = new ArrayList<>();
        generateTiles();
    }

    public void shuffle() {
        Collections.shuffle(tiles);
    }

    public ArrayList<Tile> getTiles() {
		return tiles;
	}

    
	public Tile drawTile() throws EmptyBagException {
		if (!tiles.isEmpty()) {
			return tiles.remove(0);
		}
		throw new EmptyBagException("You can't draw a tile when the bag is empty");
    }

	public Boolean addTile(Tile tile) throws BagIsFullException{
		if (this.tiles.size() >= LIMIT_BAG_CAPACITY) {
			throw new BagIsFullException("Le bag est plein !");
		}
		return this.tiles.add(tile);
	}
    private void generateTiles() {
        for (FrenchLetters letter : FrenchLetters.values()) {
            for (int i = 0; i < letter.getQuantity(); i++) {
                Tile tile = new Tile(letter);
                tiles.add(tile);
            }
        }
        shuffle();
    }
}
