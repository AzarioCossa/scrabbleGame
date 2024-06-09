package scrabble.util;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class AnimationManager {
	private AnimationManager() {
	}

	

	public static void animateFade(StackPane stackPane) {

		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), stackPane);

		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);

		fadeTransition.play();
	}

	public static void addShadowOnHover(StackPane stackPane, ImageView imageView) {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.2);

		stackPane.setOnMouseEntered(event -> imageView.setEffect(colorAdjust));

		stackPane.setOnMouseExited(event -> imageView.setEffect(null));

	}
}
