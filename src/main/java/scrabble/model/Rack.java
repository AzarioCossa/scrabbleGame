package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import scrabble.model.utils.RackIsFullException;

public class Rack {
	private ArrayList<Tile> tiles;
	public static final Integer LIMIT_RACK_CAPACITY = 7;

		
	public Rack() {
		tiles = new ArrayList<>();
	}


	public List<Tile> getTiles() {
		return tiles;
	}
	 public void shuffle() {
	        Collections.shuffle(tiles);
	  }
	

	public Boolean addTile(Tile tile) throws RackIsFullException{
		if (this.tiles.size() >= LIMIT_RACK_CAPACITY) {
			throw new RackIsFullException("Le rack est plein !");
		}
		return this.tiles.add(tile);
	}
	
	
	public Tile drawTile(Tile tile) {
	    if (tiles.contains(tile)) {
	        tiles.remove(tile);
	        return tile;
	    } else {
	        return null;
	    }
	}
	
	public void replaceJoker(Tile newTile) {
	    for (int i = 0; i < tiles.size(); i++) {
	        Tile tile = tiles.get(i);
	        if (tile.getLetter() == FrenchLetters.JOCKER) {
	            tiles.set(i, newTile);
	            break;
	        }
	    }
	}
	public Boolean removeTile(Tile tile) {
		return this.tiles.remove(tile);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(tiles);
	}

	
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
