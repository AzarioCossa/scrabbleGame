package scrabble.model;

/**
 * The Direction enum represents the four possible directions for placing tiles in the Scrabble game.
 * Each direction has a corresponding string representation.
 */
public enum Direction {

    /**
     * Represents the upward direction.
     */
    UP("up"),

    /**
     * Represents the rightward direction.
     */
    RIGHT("right"),

    /**
     * Represents the downward direction.
     */
    DOWN("down"),

    /**
     * Represents the leftward direction.
     */
    LEFT("left");

    /**
     * The string representation of the direction.
     */
    private final String stringName;

    /**
     * Constructs a Direction with the specified string name.
     *
     * @param stringName the string representation of the direction
     */
    private Direction(String stringName) {
        this.stringName = stringName;
    }

    /**
     * Returns the string representation of the direction.
     *
     * @return the string representation of the direction
     */
    public String getStringName() {
        return stringName;
    }
}
