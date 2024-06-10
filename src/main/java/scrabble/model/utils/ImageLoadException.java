package scrabble.model.utils;

/**
 * The ImageLoadException class represents an exception that is thrown when there is an error loading an image.
 */
public class ImageLoadException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ImageLoadException with the specified detail message.
     * 
     * @param message the detail message
     */
    public ImageLoadException(String message) {
        super(message);
    }
}
