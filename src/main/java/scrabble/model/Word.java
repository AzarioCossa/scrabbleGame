package scrabble.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Word {
    private Map<Position, Tile> tiles;

    public Word(Map<Position, Tile> tiles) {
    	this.tiles = tiles != null ? tiles : new HashMap<>();
    }
    public Word() {
    	this(null);
    }

    public Boolean addTile(Tile tile, Position position) {
        if (tiles.containsKey(position)) {
            return false;
        }
        tiles.put(position, tile);
        return true;
    }

    public Map<Position, Tile> getTiles() {
        return tiles;
    }
    
    public int size() {
    	return tiles.size();
    }
    
    public Boolean containsKey(Position pos) {
    	return tiles.containsKey(pos);
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
        Word other = (Word) obj;
        return Objects.equals(tiles, other.tiles);
    }
    @Override
    public String toString() {
        return tiles.entrySet().stream()
            .map(entry -> "Position: " + entry.getKey().row() + ", " + entry.getKey().column() + " Letter: " + entry.getValue().getLetter())
            .collect(Collectors.joining(", "));
    }
}
