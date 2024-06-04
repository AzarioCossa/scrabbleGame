package scrabble.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scrabble.model.GameBoard;
import scrabble.model.Square;
import scrabble.model.SquareStar;
import scrabble.model.Bag;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.User;
import scrabble.model.utils.EmptyBagException;
import scrabble.model.utils.RackIsFullException;
import scrabble.model.Position;

import java.util.Map;

public class ScrabbleController {

    private static final int BOARD_SIZE = 15;
    private static final int MIDDLE_INDEX = BOARD_SIZE / 2;

    private GameBoard gameBoard;
    private Bag bag;
    private User user;

    @FXML
    private GridPane test;

    @FXML
    private HBox idRack;
    @FXML
    private Button btnSubmit;

    @FXML
    public void initialize() {
        gameBoard = new GameBoard();
        bag = new Bag();
        user = new User("Player", initializeRack());
        generateBoard();
        displayRack();

        btnSubmit.setOnAction(event -> handleSubmit());
    }

    private void generateBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                StackPane stack = new StackPane();
                Rectangle rect = new Rectangle();

                rect.widthProperty().bind(this.test.widthProperty().divide(BOARD_SIZE));
                rect.heightProperty().bind(this.test.heightProperty().divide(BOARD_SIZE));

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
    
    private void displayRack() {
        this.idRack.getChildren().clear();
        this.idRack.setAlignment(Pos.CENTER);

        for (Tile tile : user.getRack().getTiles()) {
            StackPane stack = new StackPane();
            stack.setAlignment(Pos.CENTER);

            Rectangle rect = new Rectangle();
            rect.widthProperty().bind(this.test.widthProperty().divide(BOARD_SIZE));
            rect.heightProperty().bind(this.test.heightProperty().divide(BOARD_SIZE));

            rect.setFill(Color.BEIGE);
            rect.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");

            Label tileLabel = new Label(tile.getLetter().toString());
            tileLabel.setAlignment(Pos.CENTER);

            stack.getChildren().addAll(rect, tileLabel);
            DndTilesController.manageSourceDragAndDrop(stack, tile);
            this.idRack.getChildren().add(stack);
        }
    }


    private void handleSubmit() {
        Map<Position, Tile> playedTiles = DndTilesController.getPlayedTiles();
        if (validateWords(playedTiles)) {
            finalizeTilesOnBoard(playedTiles);
            removeTilesFromRack(playedTiles);
            refillRack(); // This will update the display of the rack
            DndTilesController.clearPlayedTiles();
        } else {
            System.out.println("Invalid word placement.");
            DndTilesController.returnTilesToRack(user.getRack(), idRack);
        }
    }


    private boolean isAdjacentToExistingTile(Position position) {
        int row = position.row();
        int col = position.column();
        Position[] neighbors = {
            new Position(row - 1, col),
            new Position(row + 1, col),
            new Position(row, col - 1),
            new Position(row, col + 1)
        };

        for (Position neighbor : neighbors) {
            if (!gameBoard.isEmpty(neighbor)) {
                return true;
            }
        }
        return false;
    }

 
    private boolean validateWords(Map<Position, Tile> playedTiles) {
        boolean coversCenter = playedTiles.keySet().stream().anyMatch(position ->
            position.row() == MIDDLE_INDEX && position.column() == MIDDLE_INDEX
        );

        if (gameBoard.isEmpty() && !coversCenter) {
            return false;
        }

        boolean touchesExistingTile = playedTiles.keySet().stream().anyMatch(this::isAdjacentToExistingTile);
        if (gameBoard.isEmpty() || touchesExistingTile) {
            return true;
        }

        return false;
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
        displayRack(); // Ensure the display is updated with the new tiles
    }

    private void removeTilesFromRack(Map<Position, Tile> playedTiles) {
        Rack rack = user.getRack();
        for (Tile tile : playedTiles.values()) {
            rack.removeTile(tile);
        }
        // No need to call displayRack here
    }

   

}
