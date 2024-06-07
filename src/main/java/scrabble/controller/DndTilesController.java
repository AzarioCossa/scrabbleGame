package scrabble.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import scrabble.model.JokerTile;
import scrabble.model.Position;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.utils.RackIsFullException;
import scrabble.util.AlertManager;
import scrabble.util.ImageLoaderManager;

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
    

    public static void manageTargetDragAndDrop(StackPane target, ImageView targetRect, Position position) {
        target.setOnDragOver(event -> {

            if (event.getGestureSource() instanceof StackPane && event.getDragboard().hasContent(TILE_FORMAT)) {
                // Check if the target position is already occupied
                if (!playedTiles.containsKey(position)) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            }
            event.consume();
        });

        target.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            //verification que si la cas contient déjà un tile on ne puisse pas superposer 
            if (dragboard.hasContent(TILE_FORMAT) && !playedTiles.containsKey(position)) {
            	Tile tile = (Tile) dragboard.getContent(TILE_FORMAT);

            	StackPane sourceStack = (StackPane) event.getGestureSource();
            	sourceStack.getChildren().clear();

            	StackPane tileStack = new StackPane();

            	// Créer l'objet ImageView avec l'image de la tuile
            	ImageView imageView = new ImageView(ImageLoaderManager.loadCardImage(tile.getLetter().toString()));

            	// Lier la taille de l'image à la taille de la case de destination (targetRect)
            	imageView.fitWidthProperty().bind(targetRect.fitWidthProperty());
            	imageView.fitHeightProperty().bind(targetRect.fitHeightProperty());

            	
            	tileStack.getChildren().addAll(imageView);

            	target.getChildren().add(tileStack);
            	System.out.println(position);
            	playedTilesVisual.put(position, tileStack);

            	if (tile instanceof JokerTile) {
                    AlertManager.showJokerLetterSelectionAlert((JokerTile) tile);
                    System.out.println("qfsgd");
                   System.out.println( ((JokerTile) tile).getJokerLetter());
                }
            	playedTiles.put(position, tile);


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

 	        ImageView img = null;

 	        try {
 	            img = new ImageView(ImageLoaderManager.loadCardImage(tile.getLetter().toString()));
 	            img.setFitWidth(60);
 	            img.setFitHeight(60);
 	            //img.fitWidthProperty().bind(this.test.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
 	            //img.fitHeightProperty().bind(this.test.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
 	        } catch (IllegalArgumentException e) {
 	            e.printStackTrace(); // ou gestion appropriée de l'erreur
 	            continue; // sauter cette tuile si l'image n'est pas trouvée
 	        }

 	        stack.getChildren().add(img);
 	        DndTilesController.manageSourceDragAndDrop(stack, tile);
 	        idRack.getChildren().add(stack);
        }

        clearPlayedTiles();
    }
    
    public static void manageBagOver(DragEvent event, ScrabbleController controller) {
        if (event.getDragboard().hasContent(TILE_FORMAT)) {
        
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            event.consume();
        }
    }

    public static Tile manageBagDropped(DragEvent event, ScrabbleController controller, Rack rack) {
        Dragboard db = event.getDragboard();
        Tile tile = (Tile) db.getContent(TILE_FORMAT);

        boolean success = false;
        if (db.hasContent(TILE_FORMAT)) {
        	System.out.println("qdsf");
            String tileLetter = db.getString();
            
            ImageView imageView = (ImageView) event.getSource();
            //controller.exchangeTile(rack, tile);
            
            //controller.displayRack();
            success = true;
            
        }
        event.setDropCompleted(success);
        event.consume();
        return tile;
    }

}
