package scrabble.model.utils;

/**
 * The TilePlacementException class represents an exception that is thrown when there is an error placing a tile on the game board.
 */
public class TilePlacementException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new TilePlacementException with the specified detail message.
     * 
     * @param message the detail message
     */
    public TilePlacementException(String message) {
        super(message);
    }
}
