package scrabble.gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import scrabble.controller.DndTilesController;
import scrabble.model.BoardSizeConstants;
import scrabble.model.GameBoard;
import scrabble.model.Position;
import scrabble.model.Square;
import scrabble.model.utils.ImageLoadException;
import scrabble.util.AnimationManager;
import scrabble.util.ImageLoaderManager;

public class GameSquarePane extends StackPane {

	private GridPane visualGameBoard;
	private GameBoard physicGameBoard;
	private Position position;
	
	public GameSquarePane(GridPane visualGameBoard,GameBoard physicGameBoard,Position position) throws ImageLoadException {
		this.physicGameBoard = physicGameBoard;
		this.visualGameBoard = visualGameBoard;
		this.position = position;
		ImageView imageView = new ImageView();

		imageView.fitWidthProperty().bind(this.visualGameBoard.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
		imageView.fitHeightProperty().bind(this.visualGameBoard.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
		this.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		Square square = this.physicGameBoard.getSquares()[position.row()-1][position.column()-1];

		imageView.setImage(ImageLoaderManager.loadSquareImage(square.getSquareType()));

		this.getChildren().add(imageView);
		DndTilesController.manageTargetDragAndDrop(this, imageView, this.position);
		AnimationManager.animateFade(this);
		AnimationManager.addShadowOnHover(this, imageView);
		
	}

	
}
