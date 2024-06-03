package scrabble.controller;

import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scrabble.model.Tile;

import java.util.ArrayList;
import java.util.List;

public class DndTilesController {
    private static final DataFormat TILE_FORMAT = new DataFormat("scrabble.tile");
    private static final List<Tile> playedTiles = new ArrayList<>();

    public static void manageSourceDragAndDrop(StackPane source, Tile tile) {
        source.setOnDragDetected(event -> {
            Dragboard dragboard = source.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboard = new ClipboardContent();
            clipboard.put(TILE_FORMAT, tile);
            dragboard.setContent(clipboard);
            event.consume();
        });
    }

    public static void manageTargetDragAndDrop(StackPane target, Rectangle targetRect) {
        target.setOnDragOver(event -> {
            if (event.getGestureSource() instanceof StackPane && event.getDragboard().hasContent(TILE_FORMAT)) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        target.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasContent(TILE_FORMAT)) {
                // Retrieve the tile data from the dragboard
                Tile tile = (Tile) dragboard.getContent(TILE_FORMAT);

                // Add the tile to the list of played tiles
                playedTiles.add(tile);

                // Remove the tile from the rack visually
                StackPane sourceStack = (StackPane) event.getGestureSource();
                sourceStack.getChildren().clear();

                // Create a new StackPane with the tile's rectangle and label
                StackPane tileStack = new StackPane();
                Rectangle tileRect = new Rectangle();
                tileRect.widthProperty().bind(targetRect.widthProperty());
                tileRect.heightProperty().bind(targetRect.heightProperty());
                tileRect.setFill(Color.BEIGE);
                tileRect.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
                Label tileLabel = new Label(tile.getLetter().toString());
                tileStack.getChildren().addAll(tileRect, tileLabel);

                // Add the tile StackPane to the target StackPane
                target.getChildren().add(tileStack);

                success = true;
            }

            event.setDropCompleted(success);
            event.consume();
        });
    }

    public static List<Tile> getPlayedTiles() {
        return playedTiles;
    }

    public static void clearPlayedTiles() {
        playedTiles.clear();
    }
}
