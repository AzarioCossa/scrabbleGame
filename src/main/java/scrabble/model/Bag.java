package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import scrabble.model.utils.BagIsFullException;
import scrabble.model.utils.EmptyBagException;
import scrabble.model.utils.RackIsFullException;

public class Bag {
    private ArrayList<Tile> tiles;
    public static final Integer LIMIT_BAG_CAPACITY = 102;
    
    public Bag() {
        tiles = new ArrayList<>();
        generateTiles();
    }

    public void shuffle() {
        Collections.shuffle(tiles);
    }

    public List<Tile> getTiles() {
		return tiles;
	}

    
	public Tile drawTile() throws EmptyBagException {
		if (!tiles.isEmpty()) {
			return tiles.remove(0);
		}
		throw new EmptyBagException("You can't draw a tile when the bag is empty");
    }
	
	public int size() {
		return tiles.size();
	}
	

	public Boolean addTile(Tile tile) throws BagIsFullException{
		if (this.tiles.size() >= LIMIT_BAG_CAPACITY) {
			throw new BagIsFullException("Le bag est plein !");
		}
		return this.tiles.add(tile);
	}
	public Boolean isEmpty() 
	{
		return this.tiles.isEmpty();
	}
	
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

}
