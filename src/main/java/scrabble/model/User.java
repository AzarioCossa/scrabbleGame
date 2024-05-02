package scrabble.model;

import java.util.Objects;

public class User {
	private Rack rack;
	private final String name;
	
	
	public User(Rack rack, String name) {
		this.rack = rack;
		this.name = name;
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
