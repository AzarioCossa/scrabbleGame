package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;

public class Bag {
    private ArrayList<Tile> tiles;

    public Bag() {
        tiles = new ArrayList<>();
        generateTiles();
    }

    public void shuffle() {
        Collections.shuffle(tiles);
    }

    public Boolean addTile(Tile tile) {
        return this.tiles.add(tile);
    }

    public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public Tile drawTile() {
        return tiles.remove(0);
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
