package scrabble.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scrabble.model.JokerTile;
import scrabble.model.Position;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.utils.RackIsFullException;
import scrabble.util.AlertManager;

import java.util.HashMap;
import java.util.Map;

public class DndTilesController {
    private static final DataFormat TILE_FORMAT = new DataFormat("scrabble.tile");
    private static final Map<Position, Tile> playedTiles = new HashMap<>();
    private static final Map<Position, StackPane> playedTilesVisual = new HashMap<>();

    public static void manageSourceDragAndDrop(StackPane source, Tile tile) {
        source.setOnDragDetected(event -> {
            Dragboard dragboard = source.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboard = new ClipboardContent();
            clipboard.put(TILE_FORMAT, tile);
            dragboard.setContent(clipboard);
            event.consume();
        });
    }

    public static void manageTargetDragAndDrop(StackPane target, Rectangle targetRect, Position position) {
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
                Tile tile = (Tile) dragboard.getContent(TILE_FORMAT);
                playedTiles.put(position, tile);

                StackPane sourceStack = (StackPane) event.getGestureSource();
                sourceStack.getChildren().clear();

                StackPane tileStack = new StackPane();
                Rectangle tileRect = new Rectangle();
                tileRect.widthProperty().bind(targetRect.widthProperty());
                tileRect.heightProperty().bind(targetRect.heightProperty());
                tileRect.setFill(Color.BEIGE);
                tileRect.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
                Label tileLabel = new Label(tile.getLetter().toString());
                tileStack.getChildren().addAll(tileRect, tileLabel);
                if (tile instanceof JokerTile) {
                    AlertManager.showJokerLetterSelectionAlert((JokerTile) tile);
                }
                target.getChildren().add(tileStack);
                playedTilesVisual.put(position, tileStack);

                success = true;
            }

            event.setDropCompleted(success);
            event.consume();
        });
    }

    public static Map<Position, Tile> getPlayedTiles() {
        return playedTiles;
    }

    public static void clearPlayedTiles() {
        playedTiles.clear();
        playedTilesVisual.clear();
    }

    public static void finalizeTilesOnBoard() {
        playedTilesVisual.clear();
    }

    public static void returnTilesToRack(Rack rack, HBox idRack) {
        for (Tile tile : playedTiles.values()) {
            try {
                if (rack.getTiles().size() < Rack.LIMIT_RACK_CAPACITY) {
                    rack.addTile(tile);
                }
            } catch (RackIsFullException e) {
                System.out.println("Rack is full, cannot return tile.");
            }
        }

        for (StackPane tileStack : playedTilesVisual.values()) {
            tileStack.getChildren().clear();
        }

        idRack.getChildren().clear();
        idRack.setAlignment(Pos.CENTER);

        for (Tile tile : rack.getTiles()) {
            StackPane stack = new StackPane();
            stack.setAlignment(Pos.CENTER);

            Rectangle rect = new Rectangle();
            rect.setWidth(50);
            rect.setHeight(50);

            rect.setFill(Color.BEIGE);
            rect.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");

            Label tileLabel = new Label(tile.getLetter().toString());
            tileLabel.setAlignment(Pos.CENTER);

            stack.getChildren().addAll(rect, tileLabel);
            manageSourceDragAndDrop(stack, tile);
            idRack.getChildren().add(stack);
        }

        clearPlayedTiles();
    }
}
