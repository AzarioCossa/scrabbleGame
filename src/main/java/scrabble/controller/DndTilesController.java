package scrabble.controller;

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
    private static final int TILE_SIZE = 65;
    private static final int BOARD_SIZE = 15;


    public static void manageSourceDragAndDrop(Rectangle source, Tile tile) {
        source.setOnDragDetected(event -> {
            Dragboard dragboard = source.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboard = new ClipboardContent();
            clipboard.put(TILE_FORMAT, tile);
            dragboard.setContent(clipboard);
            event.consume();
        });
    }

    public static void manageTargetDragAndDrop(Rectangle target) {
        target.setOnDragOver(event -> {
            if (event.getGestureSource() instanceof Rectangle && event.getDragboard().hasContent(TILE_FORMAT)) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        target.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasContent(TILE_FORMAT)) {
                // Récupérer les données de la tuile du dragboard
                Tile tile = (Tile) dragboard.getContent(TILE_FORMAT);

                // Ajouter la tuile à la liste des tuiles jouées
                playedTiles.add(tile);

                // Supprimer visuellement le rectangle du rack
                StackPane sourceStack = (StackPane) ((Rectangle) event.getGestureSource()).getParent();
                sourceStack.getChildren().remove(0);

                // Ajouter visuellement le rectangle à la cible sur le plateau de jeu
                StackPane targetStack = (StackPane) target.getParent();
                Rectangle sourceRect = new Rectangle();
                sourceRect.setWidth(TILE_SIZE);
                sourceRect.setHeight(TILE_SIZE);
                sourceRect.setFill(Color.GRAY); // Ajoutez la couleur de la tuile ici
                targetStack.getChildren().add(sourceRect);

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
