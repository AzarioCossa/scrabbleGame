package scrabble.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scrabble.model.GameBoard;
import scrabble.model.Square;

import scrabble.model.Bag;
import scrabble.model.BoardSizeConstants;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.User;
import scrabble.model.Word;
import scrabble.model.utils.BagIsFullException;
import scrabble.model.utils.EmptyBagException;
import scrabble.model.utils.RackIsFullException;
import scrabble.util.ImageLoaderManager;
import scrabble.util.WordsManager;
import scrabble.model.Position;

import java.util.Map;

public class ScrabbleController {

	private GameBoard gameBoard;
	private Bag bag;
	private User user;
	private WordsManager wordsManager;
	private boolean hasExchangedThisTurn = false;

	@FXML
	private GridPane test;

	@FXML
	private HBox idRack;
	@FXML
	private Button btnSubmit;

	@FXML
	private Label lblScore;

	@FXML
	private ImageView bagImgView;

	@FXML
	public void initialize() {
		this.gameBoard = new GameBoard();
		this.bag = new Bag();
		this.user = new User("Player", initializeRack());
		this.wordsManager = new WordsManager(gameBoard);
		generateBoard();
		displayRack();
		lblScore.textProperty().bind(Bindings.convert(user.scoreProperty()));
		btnSubmit.setOnAction(event -> handleSubmit());
		bagImgView.setOnDragOver(event -> DndTilesController.manageBagOver(event, this));
		bagImgView.setOnDragDropped(event -> {
			Tile tile = DndTilesController.manageBagDropped(event, this, this.user.getRack());
			if (tile != null) {
				this.exchangeTile(this.user.getRack(), tile);
				this.displayRack();
			}
		});

	}

	private void generateBoard() {
		for (int row = 0; row < BoardSizeConstants.BOARD_SIZE; row++) {
			for (int col = 0; col < BoardSizeConstants.BOARD_SIZE; col++) {
				StackPane stack = new StackPane();
				ImageView imageView = new ImageView();

				imageView.fitWidthProperty().bind(this.test.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
				imageView.fitHeightProperty().bind(this.test.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
				stack.setStyle("-fx-border-color: black; -fx-border-width: 1;");
				Square square = gameBoard.getSquares()[row][col];

				imageView.setImage(ImageLoaderManager.loadSquareImage(square.getSquareType()));

				stack.getChildren().add(imageView);
				DndTilesController.manageTargetDragAndDrop(stack, imageView, new Position(row + 1, col + 1));

				this.test.add(stack, col, row);
			}
		}
	}

	private Rack initializeRack() {
		Rack rack = new Rack();
		try {
			for (int i = 0; i < Rack.LIMIT_RACK_CAPACITY; i++) {
				rack.addTile(bag.drawTile());
			}
		} catch (EmptyBagException | RackIsFullException e) {
			System.out.println(e.getMessage());
		}
		return rack;
	}

	public void displayRack() {
		this.idRack.getChildren().clear();
		this.idRack.setAlignment(Pos.CENTER);

		for (Tile tile : user.getRack().getTiles()) {
			StackPane stack = new StackPane();
			stack.setAlignment(Pos.CENTER);

			ImageView img = null;

			try {
				img = new ImageView(ImageLoaderManager.loadCardImage(tile.getLetter().toString()));
				img.fitWidthProperty().bind(this.test.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
				img.fitHeightProperty().bind(this.test.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
			} catch (IllegalArgumentException e) {
				e.printStackTrace(); 
				continue;
			}

			stack.getChildren().add(img);
			DndTilesController.manageSourceDragAndDrop(stack, tile);
			this.idRack.getChildren().add(stack);
		}
	}

	@FXML
	private void onShuffleButtonClicked() {

		this.user.getRack().shuffle();
		this.displayRack();

	}

	@FXML
	private void handleSubmit() {
		Map<Position, Tile> playedTiles = DndTilesController.getPlayedTiles();
		System.out.println(playedTiles.containsKey(new Position(8, 8)));
		if (this.wordsManager.validateWords(playedTiles)) {

			this.finalizeTilesOnBoard(playedTiles);
			this.removeTilesFromRack(playedTiles);
			Word word = createWordFromPlayedTiles(playedTiles);

			this.user.addWord(word, this.gameBoard);
			this.refillRack();
			DndTilesController.clearPlayedTiles();
			this.hasExchangedThisTurn = false;

		} else {
			System.out.println("Invalid word placement.");
			DndTilesController.returnTilesToRack(user.getRack(), idRack);
		}
	}

	private Word createWordFromPlayedTiles(Map<Position, Tile> playedTiles) {
		Word word = new Word();
		for (Map.Entry<Position, Tile> entry : playedTiles.entrySet()) {
			Position position = entry.getKey();
			Tile tile = entry.getValue();
			word.addTile(tile, position);
		}
		return word;
	}

	private void finalizeTilesOnBoard(Map<Position, Tile> playedTiles) {
		for (Map.Entry<Position, Tile> entry : playedTiles.entrySet()) {
			Position position = entry.getKey();
			Tile tile = entry.getValue();
			gameBoard.placeTileGameBoard(tile, position.row() - 1, position.column() - 1);
		}

		DndTilesController.finalizeTilesOnBoard();
	}

	private void refillRack() {
		Rack rack = user.getRack();
		try {
			while (rack.getTiles().size() < Rack.LIMIT_RACK_CAPACITY && !bag.isEmpty()) {
				rack.addTile(bag.drawTile());
			}
		} catch (EmptyBagException | RackIsFullException e) {
			System.out.println(e.getMessage());
		}
		displayRack();
	}

	public void exchangeTile(Rack rack, Tile tile) {
		if (hasExchangedThisTurn) {
			System.out.println("You can only exchange tiles once per turn.");
			return;
		}

		if (bag.size() < 7) {
			System.out.println("Not enough tiles in the bag to exchange.");
			return;
		}

		try {
			rack.removeTile(tile);
			Tile newTile = bag.drawTile();
			rack.addTile(newTile);

			try {
				bag.addTile(tile);
			} catch (BagIsFullException e) {
				System.out.println(e.getMessage());
			}
			bag.shuffle();
			this.hasExchangedThisTurn = true;
		} catch (EmptyBagException e) {
			System.out.println("The bag is empty: " + e.getMessage());
		} catch (RackIsFullException e) {
			System.out.println("The rack is full: " + e.getMessage());
		}
	}

	private void removeTilesFromRack(Map<Position, Tile> playedTiles) {
		Rack rack = user.getRack();
		for (Tile tile : playedTiles.values()) {
			System.out.println("supression");
			System.out.println(rack.removeTile(tile));
		}
	}

}
