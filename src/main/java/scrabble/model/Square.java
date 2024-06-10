package scrabble.model;

/**
 * The Square class represents a square on the Scrabble game board.
 * It holds information about the tile placed on the square and the type of the square.
 */
public class Square {

    /**
     * The tile placed on the square, null if the square is empty.
     */
    protected Tile tile;

    /**
     * The type of the square (e.g., standard, triple word, double letter).
     */
    protected SquareType squareType;

    /**
     * Constructs a new Square with the specified square type and no tile placed on it.
     * 
     * @param squareType the type of the square
     */
    protected Square(SquareType squareType) {
        this.tile = null;
        this.squareType = squareType;
    }

    /**
     * Returns the type of this square.
     * 
     * @return the square type
     */
    public SquareType getSquareType() {
        return this.squareType;
    }

    /**
     * Returns the tile placed on this square, null if the square is empty.
     * 
     * @return the tile placed on this square
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * Places a tile on this square.
     * 
     * @param tile the tile to place on this square
     */
    public void placeTileSquare(Tile tile) {
        this.tile = tile;
    }

    /**
     * Checks if this square is empty.
     * 
     * @return true if this square is empty, false otherwise
     */
    public Boolean isEmpty() {
        return this.tile == null;
    }
}
