package scrabble.util;

import javafx.scene.image.Image;
import scrabble.model.SquareType;

public class ImageLoaderManager {
	private ImageLoaderManager() {}

    public static Image loadCardImage(String value) throws IllegalArgumentException {
        Image image = null;
        try {
            image = new Image(ImageLoaderManager.class.getResourceAsStream("/images/bag/" + value + ".png"));
            if (image.isError()) {
                throw new IllegalArgumentException("Image not found for value: " + value);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error loading image for value: " + value, e);
        }
        return image;
    }
    public static Image loadSquareImage(SquareType squareType) throws IllegalArgumentException {
        Image image = null;
        try {
            image = new Image(ImageLoaderManager.class.getResourceAsStream("/images/general/" + squareType.name() + ".png"));
            if (image.isError()) {
                throw new IllegalArgumentException("Image not found for value: " + squareType.toString());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error loading image for value: " + squareType.toString(), e);
        }
        return image;
    }
}