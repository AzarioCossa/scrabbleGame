package scrabble.model;

import java.util.ArrayList;
import java.util.Objects;

public class Rack {
	private ArrayList<Tile> tiles;

	public Rack(ArrayList<Tile> initialTiles) {
		tiles = new ArrayList<>(initialTiles);
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public Boolean addTile(Tile tile) {
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
