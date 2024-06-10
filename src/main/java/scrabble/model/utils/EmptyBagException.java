package scrabble.model.utils;

/**
 * The EmptyBagException class represents an exception that is thrown when attempting to draw a tile from an empty bag.
 */
public class EmptyBagException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new EmptyBagException with the specified detail message.
     * 
     * @param message the detail message
     */
    public EmptyBagException(String message) {
        super(message);
    }
}
