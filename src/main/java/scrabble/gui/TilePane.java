package scrabble.gui;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import scrabble.controller.DndTilesController;
import scrabble.model.BoardSizeConstants;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.utils.ImageLoadException;
import scrabble.util.ImageLoaderManager;

public class TilePane extends StackPane {

	public TilePane(GridPane visualGameBoard, Tile tile, Rack rack) throws ImageLoadException {

		this.setAlignment(Pos.CENTER);

		ImageView img = new ImageView(ImageLoaderManager.loadTileImage(tile.getLetter().toString()));
		img.fitWidthProperty().bind(visualGameBoard.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
		img.fitHeightProperty().bind(visualGameBoard.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));

		this.getChildren().add(img);
		DndTilesController.manageSourceDragAndDrop(this, tile);
		DndTilesController.setupDragAndDrop(this,tile,rack);
		visualGameBoard.getChildren().add(this);
	}

	public TilePane(ImageView targetRect, Tile tile) throws ImageLoadException {

		this.setAlignment(Pos.CENTER);

		ImageView imageView;

		imageView = new ImageView(ImageLoaderManager.loadTileImage(tile.getLetter().toString()));
		imageView.fitWidthProperty().bind(targetRect.fitWidthProperty());
		imageView.fitHeightProperty().bind(targetRect.fitHeightProperty());
		DndTilesController.manageSourceDragAndDrop(this, tile);
		
		this.getChildren().add(imageView);

	}

}
