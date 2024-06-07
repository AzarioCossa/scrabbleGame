package scrabble.util;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class AnimationManager {
	private AnimationManager () {}
	
    public static void animateStackPane(StackPane stackPane) {
        Duration animationDuration = Duration.seconds(1.5);
        double startY = -100;
        double endY = 0;

      
        TranslateTransition translateTransition = new TranslateTransition(animationDuration, stackPane);
        translateTransition.setFromY(startY);
        translateTransition.setToY(endY);
        translateTransition.play();
    }
    public static void animateFade(StackPane stackPane) {
    
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), stackPane);

        
        fadeTransition.setFromValue(0); 
        fadeTransition.setToValue(1); 

       
        fadeTransition.play();
    }
    public static void addShadowOnHover(StackPane stackPane, ImageView imageView) {
        Rectangle grayFilter = new Rectangle();
        grayFilter.setFill(Color.BLACK);
        grayFilter.setOpacity(0);

        stackPane.getChildren().add(grayFilter);

        
        stackPane.setOnMouseEntered(event -> {
            Bounds boundsInLocal = imageView.getBoundsInLocal();
            grayFilter.setWidth(boundsInLocal.getWidth());
            grayFilter.setHeight(boundsInLocal.getHeight());
            grayFilter.setOpacity(0.3);
        });
        stackPane.setOnDragOver(event -> {
            Bounds boundsInLocal = imageView.getBoundsInLocal();
            grayFilter.setWidth(boundsInLocal.getWidth());
            grayFilter.setHeight(boundsInLocal.getHeight());
            grayFilter.setOpacity(0.3);
        });


        stackPane.setOnMouseExited(event -> grayFilter.setOpacity(0));
        stackPane.setOnDragExited(event -> grayFilter.setOpacity(0));

    }}
