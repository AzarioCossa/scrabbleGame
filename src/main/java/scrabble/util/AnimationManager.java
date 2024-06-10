package scrabble.util;

import javafx.animation.FadeTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * The AnimationManager class provides utility methods for managing animations in the Scrabble game.
 */
public class AnimationManager {

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private AnimationManager() {
    }

    /**
     * Animates the fade effect on a StackPane.
     * 
     * @param stackPane the StackPane to animate
     */
    public static void animateFade(StackPane stackPane) {

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), stackPane);

        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        fadeTransition.play();
    }

    /**
     * Adds a shadow effect to an ImageView when hovered over a StackPane.
     * 
     * @param stackPane the StackPane to apply the hover effect
     * @param imageView the ImageView to add the shadow effect
     */
    public static void addShadowOnHover(StackPane stackPane, ImageView imageView) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.2);

        stackPane.setOnMouseEntered(event -> imageView.setEffect(colorAdjust));

        stackPane.setOnMouseExited(event -> imageView.setEffect(null));
    }
}
