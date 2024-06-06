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
                Rectangle rect = new Rectangle();

                rect.widthProperty().bind(this.test.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
                rect.heightProperty().bind(this.test.heightProperty().divide(BoardSizeConstants.BOARD_SIZE));

                Square square = gameBoard.getSquares()[row][col];

                if (square instanceof SquareStar) {
                    rect.setFill(Color.LIGHTPINK);
                } else {
                    rect.setFill(Color.GREEN);
                }

                rect.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");

                stack.getChildren().add(rect);
                DndTilesController.manageTargetDragAndDrop(stack, rect, new Position(row, col));

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
    public static Image loadCardImage(char value) throws IllegalArgumentException  {
       
        String imagePath = "src/main/resources/images/bag/a.png";
        File file = new File(imagePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Image file not found: " + imagePath);
        }
        return new Image(file.toURI().toString());
    }
    
    private void displayRack() {
        this.idRack.getChildren().clear();
        this.idRack.setAlignment(Pos.CENTER);

        for (Tile tile : user.getRack().getTiles()) {
            StackPane stack = new StackPane();
            stack.setAlignment(Pos.CENTER);

            Rectangle rect = new Rectangle();
            rect.widthProperty().bind(this.test.widthProperty().divide(BoardSizeConstants.BOARD_SIZE));
            rect.heightProperty().bind(this.test.heightProperty().divide(BoardSizeConstants.BOARD_SIZE));

            rect.setFill(Color.BEIGE);
            rect.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");

            Label tileLabel = new Label(tile.getLetter().toString());
            tileLabel.setAlignment(Pos.CENTER);

            stack.getChildren().addAll(rect, tileLabel);
            DndTilesController.manageSourceDragAndDrop(stack, tile);
            this.idRack.getChildren().add(stack);
        }
    }
    /*
    
    private void displayRack() {
        this.idRack.getChildren().clear();
        this.idRack.setAlignment(Pos.CENTER);

        for (Tile tile : user.getRack().getTiles()) {
            // Créer le StackPane pour contenir l'image
            StackPane stack = new StackPane();
            stack.setAlignment(Pos.CENTER);

           

            // Créer l'ImageView pour afficher l'image
            ImageView imageView = new ImageView(loadCardImage('a'));
            imageView.setFitWidth(50); // Ajuster la taille de l'image selon vos besoins
            imageView.setFitHeight(50);

            // Ajouter l'ImageView au StackPane
            stack.getChildren().add(imageView);

            // Ajouter le StackPane au HBox du rack
            this.idRack.getChildren().add(stack);
        }
    }
    */




    private void handleSubmit() {
        Map<Position, Tile> playedTiles = DndTilesController.getPlayedTiles();
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
            gameBoard.placeTileGameBoard(tile, position.row(), position.column());
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
            rack.removeTile(tile);
        }
    }

   

}
