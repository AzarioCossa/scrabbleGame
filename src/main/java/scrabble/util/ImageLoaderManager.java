package scrabble.util;

import javafx.scene.image.Image;
import scrabble.model.SquareType;
import scrabble.model.utils.ImageLoadException;

/**
 * The ImageLoaderManager class provides utility methods for loading images in the Scrabble game.
 */
public class ImageLoaderManager {

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private ImageLoaderManager() {}

    /**
     * Loads the image of a tile based on its value.
     * 
     * @param value the value of the tile
     * @return the image of the tile
     * @throws ImageLoadException if there is an error loading the image
     */
    public static Image loadTileImage(String value) throws ImageLoadException {
        Image image = null;
        try {
            image = new Image(ImageLoaderManager.class.getResourceAsStream("/images/bag/" + value + ".png"));
            if (image.isError()) {
                throw new ImageLoadException("Image not found for value: " + value);
            }
        } catch (Exception e) {
            throw new ImageLoadException("Error loading image for value: " + value);
        }
        return image;
    }
    
    /**
     * Loads the image of a square based on its type.
     * 
     * @param squareType the type of the square
     * @return the image of the square
     * @throws ImageLoadException if there is an error loading the image
     */
    public static Image loadSquareImage(SquareType squareType) throws ImageLoadException {
        Image image = null;
        try {
            image = new Image(ImageLoaderManager.class.getResourceAsStream("/images/general/" + squareType.name() + ".png"));
            if (image.isError()) {
                throw new ImageLoadException("Image not found for value: " + squareType.toString());
            }
        } catch (Exception e) {
            throw new ImageLoadException("Error loading image for value: " + squareType.toString());
        }
        return image;
    }
}
