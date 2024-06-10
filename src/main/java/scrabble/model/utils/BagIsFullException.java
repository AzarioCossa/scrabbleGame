package scrabble.model.utils;

/**
 * The BagIsFullException class represents an exception that is thrown when attempting to add a tile to a bag that is already full.
 */
public class BagIsFullException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new BagIsFullException with the specified detail message.
     * 
     * @param message the detail message
     */
    public BagIsFullException(String message) {
        super(message);
    }
}
