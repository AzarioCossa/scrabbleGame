package scrabble.model;

import java.util.ArrayList;
import java.util.Objects;

public class User {
	private Rack rack;
	private final String name;
	private Bag bag;
	private final Integer LIMIT = 7;
	
	
	public User(Bag bag, String name) {
		this.name = name;
		this.bag = bag;
		this.rack = new Rack(initializeRack());

	}
	
	public ArrayList<Tile> initializeRack() {
    	ArrayList<Tile> tile = new ArrayList<>();
        for (int i = 0; i < LIMIT; i++) {
            tile.add(this.bag.drawTile());
        }
        return tile;

    }
	
	public Rack getRack() {
		return rack;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(name, rack);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(name, other.name) && Objects.equals(rack, other.rack);
	}

}
