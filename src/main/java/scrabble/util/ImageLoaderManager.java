package scrabble.util;

import javafx.scene.image.Image;
import scrabble.model.SquareType;
import scrabble.model.utils.ImageLoadException;

public class ImageLoaderManager {
	private ImageLoaderManager() {}

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