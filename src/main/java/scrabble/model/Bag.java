package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;

public class Bag {
    private ArrayList<Tile> tiles;

    public Bag() {
        tiles = new ArrayList<>();
        generateTiles();
        shuffle();
    }

    
    public void shuffle() {
        Collections.shuffle(tiles);
    }

    
 
    
    public ArrayList<Tile> getTiles() {
		return tiles;
	}

    
	public Tile drawTile() {
		if (!tiles.isEmpty()) {
			return tiles.remove(0);
		}
		return null;
    }

	
    private void generateTiles() {
        for (Letters letter : Letters.values()) {
            for (int i = 0; i < letter.getQuantity(); i++) {
                Tile tile = new Tile(letter);
                tiles.add(tile);
            }
        }
    }
}
