package scrabble.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scrabble.model.GameBoard;
import scrabble.model.Square;
import scrabble.model.SquareStar;
import scrabble.model.Bag;
import scrabble.model.BoardSizeConstants;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.User;
import scrabble.model.Word;
import scrabble.model.utils.EmptyBagException;
import scrabble.model.utils.RackIsFullException;
import scrabble.util.ImageLoaderManager;
import scrabble.util.WordsManager;
import scrabble.model.Position;

import java.io.File;
import java.util.Map;

public class ScrabbleController {

	private GameBoard gameBoard;
	private Bag bag;
	private User user;
	private WordsManager wordsManager;

	@FXML
	private GridPane test;

	@FXML
	private HBox idRack;
	@FXML
	private Button btnSubmit;

	@FXML
	private Label lblScore;

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

	            if (square instanceof SquareStar) {
	                imageView.setImage(ImageLoaderManager.loadSquareImage("star"));
	            } else {
	                imageView.setImage(ImageLoaderManager.loadSquareImage("images"));
	            }

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

	
	private void displayRack() {
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
	            e.printStackTrace(); // ou gestion appropriée de l'erreur
	            continue; // sauter cette tuile si l'image n'est pas trouvée
	        }

	        stack.getChildren().add(img);
	        DndTilesController.manageSourceDragAndDrop(stack, tile);
	        this.idRack.getChildren().add(stack);
	    }
	}



	private void handleSubmit() {
		Map<Position, Tile> playedTiles = DndTilesController.getPlayedTiles();
		System.out.println(playedTiles.containsKey(new Position(8, 8)));
		if (this.wordsManager.validateWords(playedTiles)) {

			this.finalizeTilesOnBoard(playedTiles);
			this.removeTilesFromRack(playedTiles);
			Word word = createWordFromPlayedTiles(playedTiles);

			this.user.addWord(word);
			this.refillRack();
			DndTilesController.clearPlayedTiles();
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
			gameBoard.placeTileGameBoard(tile, position.row()-1, position.column()-1);
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

	private void removeTilesFromRack(Map<Position, Tile> playedTiles) {
		Rack rack = user.getRack();
		for (Tile tile : playedTiles.values()) {
			System.out.println("supression");
			System.out.println(rack.removeTile(tile));
		}
	}

}
