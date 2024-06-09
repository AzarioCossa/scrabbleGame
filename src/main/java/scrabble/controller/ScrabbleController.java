package scrabble.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import scrabble.model.GameBoard;
import scrabble.gui.GameSquarePane;
import scrabble.gui.TilePane;
import scrabble.model.Bag;
import scrabble.model.BoardSizeConstants;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.User;
import scrabble.model.Word;
import scrabble.model.utils.BagIsFullException;
import scrabble.model.utils.EmptyBagException;
import scrabble.model.utils.ImageLoadException;
import scrabble.model.utils.RackIsFullException;
import scrabble.util.ScenesManager;
import scrabble.util.WordsManager;
import scrabble.model.Position;

import java.util.Map;

public class ScrabbleController {

	private GameBoard gameBoard;
	private Bag bag;
	private User user;
	private WordsManager wordsManager;
	private IntegerProperty round;

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
	private Label lblRound;

	@FXML
	public void initialize() {
		this.round = new SimpleIntegerProperty(1);
		this.gameBoard = new GameBoard();
		this.bag = new Bag();
		this.user = new User("Louis", initializeRack());
		this.wordsManager = new WordsManager(gameBoard);
		try {
			generateBoard();
		} catch (ImageLoadException e) {
		
			e.printStackTrace();
		}
		displayRack();
		lblScore.textProperty().bind(Bindings.convert(user.scoreProperty()));
		lblRound.textProperty().bind(Bindings.convert(this.round));
		btnSubmit.setOnAction(event -> handleSubmit());
		bagImgView.setOnDragOver(DndTilesController::manageBagOver);
		bagImgView.setOnDragDropped(event -> {
			Tile tile = DndTilesController.manageBagDropped(event);
			if (tile != null) {
				try {
					this.exchangeTile(this.user.getRack(), tile);
				} catch (RackIsFullException | EmptyBagException | BagIsFullException e) {
					e.printStackTrace();
				}
				this.displayRack();
			}
		});
	

	}
	private void nextRound() {
        this.round.set(round.get() + 1);
	 }

	private void generateBoard() throws ImageLoadException {
		for (int row = 0; row < BoardSizeConstants.BOARD_SIZE; row++) {
			for (int col = 0; col < BoardSizeConstants.BOARD_SIZE; col++) {
				StackPane stack = new GameSquarePane(this.test,gameBoard,new Position(row+1,col+1));
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
			StackPane stack;
			try {
				stack = new TilePane(this.test,tile);
				this.idRack.getChildren().add(stack);
			} catch (ImageLoadException e) {
				e.printStackTrace();
			}
			
		}
	}

	@FXML
	private void shuffleOnButtonClicked() {

		this.user.getRack().shuffle();
		this.displayRack();

	}

	@FXML
	private void handleSubmit() {
		Map<Position, Tile> playedTiles = DndTilesController.getPlayedTiles();

		if (this.wordsManager.validateWords(playedTiles)) {
			this.finalizeTilesOnBoard(playedTiles);
			this.removeTilesFromRack(playedTiles);
			Word word = createWordFromPlayedTiles(playedTiles);

			this.user.addWord(word);
			
			this.user.incrementScore(wordsManager.calculateMoveScore(playedTiles));
			this.refillRack();
			DndTilesController.clearPlayedTiles();
			this.user.setHasExchangedThisTurn(false);
			this.nextRound();

		} else {
			System.out.println("Invalid word placement.");
			DndTilesController.returnTilesToRack();
			this.displayRack();
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
			while (rack.getTiles().size() < Rack.LIMIT_RACK_CAPACITY && Boolean.TRUE.equals(!bag.isEmpty())) {
				rack.addTile(bag.drawTile());
			}
		} catch (EmptyBagException | RackIsFullException e) {
			System.out.println(e.getMessage());
		}
		displayRack();
	}

	public void exchangeTile(Rack rack, Tile tile) throws RackIsFullException, EmptyBagException, BagIsFullException {
		if (bag.size() < 7) {
			ScenesManager.showError("Not enough tiles in the bag to exchange.");
			return;
		}

		if (this.user.hasExchangedThisTurn()) {
			ScenesManager.showError("You can only exchange tiles once per turn.");
			return;
		}

		rack.removeTile(tile);
		Tile newTile = bag.drawTile();
		rack.addTile(newTile);

		bag.addTile(tile);
		this.user.setHasExchangedThisTurn(true);
	}

	private void removeTilesFromRack(Map<Position, Tile> playedTiles) {
		Rack rack = user.getRack();
		for (Tile tile : playedTiles.values()) {
			rack.removeTile(tile);
		}
	}

}
