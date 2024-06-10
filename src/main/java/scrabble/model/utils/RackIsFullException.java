package scrabble.model.utils;

/**
 * The RackIsFullException class represents an exception that is thrown when attempting to add a tile to a rack that is already full.
 */
public class RackIsFullException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new RackIsFullException with the specified detail message.
     * 
     * @param message the detail message
     */
    public RackIsFullException(String message) {
        super(message);
    }
}
