package scrabble.controller;

import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import scrabble.gui.TilePane;
import scrabble.model.JokerTile;
import scrabble.model.Position;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.utils.ImageLoadException;
import scrabble.model.utils.RackIsFullException;
import scrabble.util.ScenesManager;

import java.util.HashMap;
import java.util.Map;

public class DndTilesController {
	private DndTilesController() {
	}

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

	public static void manageTargetDragAndDrop(StackPane target, Position position, Rack rack) {
		
		target.setOnDragOver(event -> {

			if (event.getGestureSource() instanceof StackPane && event.getDragboard().hasContent(TILE_FORMAT)
					&& !playedTiles.containsKey(position)) {
				event.acceptTransferModes(TransferMode.MOVE);
			}

			event.consume();
		});

		target.setOnDragDropped(event -> {
		    ImageView targetRect = (ImageView) target.getChildren().get(0);
			Dragboard dragboard = event.getDragboard();
			boolean success = false;
			if (dragboard.hasContent(TILE_FORMAT) && !playedTiles.containsKey(position)) {
				Tile tile = (Tile) dragboard.getContent(TILE_FORMAT);

				StackPane sourceStack = (StackPane) event.getGestureSource();
				sourceStack.getChildren().clear();

				StackPane tileStack;
				try {
					tileStack = new TilePane(targetRect, tile);
					target.getChildren().add(tileStack);
					playedTilesVisual.put(position, tileStack);
				} catch (ImageLoadException e) {
					e.printStackTrace();
				}

				if (tile instanceof JokerTile) {
					ScenesManager.showJokerLetterSelectionAlert((JokerTile) tile);
				}
				playedTiles.put(position, tile);
				rack.drawTile(tile);

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

	public static void returnTilesToRack(Rack rack) {
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
		clearPlayedTiles();

	}

	public static void manageBagOver(DragEvent event) {
		if (event.getDragboard().hasContent(TILE_FORMAT)) {

			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			event.consume();
		}
	}

	public static Tile manageBagDropped(DragEvent event) {
		Dragboard db = event.getDragboard();
		Tile tile = (Tile) db.getContent(TILE_FORMAT);

		boolean success = false;
		if (db.hasContent(TILE_FORMAT)) {

			success = true;

		}
		event.setDropCompleted(success);
		event.consume();
		return tile;
	}
	
	public static void setupDragAndDrop(StackPane tilePane, Tile tile, Rack rack) {
	    tilePane.setOnDragDetected(event -> {
	        Dragboard db = tilePane.startDragAndDrop(TransferMode.MOVE);
	        ClipboardContent content = new ClipboardContent();
	        content.put(TILE_FORMAT, tile);
	        db.setContent(content);
	        event.consume();
	    });

	    tilePane.setOnDragOver(event -> {
	        if (event != null && event.getDragboard() != null && event.getDragboard().hasContent(TILE_FORMAT)) {
	            event.acceptTransferModes(TransferMode.MOVE);
	        }
	        event.consume();
	    });

	    tilePane.setOnDragEntered(event -> {
	        if (event != null && event.getDragboard() != null && event.getDragboard().hasContent(TILE_FORMAT)) {
	            tilePane.setOpacity(0.3);
	        }
	    });

	    tilePane.setOnDragExited(event -> tilePane.setOpacity(1));

	    tilePane.setOnDragDropped(event -> {
	        if (event != null && event.getDragboard() != null) {
	            Dragboard db = event.getDragboard();
	            boolean success = false;
	            if (db.hasContent(TILE_FORMAT)) {
	                Tile sourceTile = (Tile) db.getContent(TILE_FORMAT);
	                rack.swapTiles(sourceTile, tile);
	                success = true;
	            }
	            event.setDropCompleted(success);
	            event.consume();
	        }
	    });
	}

}


