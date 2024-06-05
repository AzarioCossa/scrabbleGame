package scrabble.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Word {
    private Map<Position, Tile> tiles;

    public Word() {
        this.tiles = new HashMap<>();
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
}
