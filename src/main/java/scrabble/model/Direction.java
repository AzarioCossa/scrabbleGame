package scrabble.model;

public enum Direction {

	UP("up"),
	RIGHT("right"),
	DOWN("down"),
	LEFT("left");

	
	private final String stringName;
	
	private Direction(String stringName) {
		this.stringName = stringName;
	}

	public String getStringName() {
		return stringName;
	}
}