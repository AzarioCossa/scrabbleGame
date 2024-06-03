package scrabble.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
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

public class ScrabbleController {

    private static final int TILE_SIZE = 65;
    private static final int BOARD_SIZE = 15;

    private GameBoard gameBoard;
    private Bag bag;
    private User user;

    @FXML
    private GridPane test;

    @FXML
    private HBox idRack;

    @FXML
    public void initialize() {
        gameBoard = new GameBoard();
        bag = new Bag();
        user = new User("Player", initializeRack());
        generateBoard();
        displayRack();
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

            rect.setWidth(TILE_SIZE);
            rect.setHeight(TILE_SIZE);

            rect.setFill(Color.BEIGE);
            rect.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");

            Label tileLabel = new Label(tile.getLetter().toString());
            tileLabel.setAlignment(Pos.CENTER);

            stack.getChildren().addAll(rect, tileLabel);
            this.idRack.getChildren().add(stack);
        }
    }
}
