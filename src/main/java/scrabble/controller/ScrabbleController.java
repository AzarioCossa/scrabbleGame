package scrabble.controller;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scrabble.model.GameBoard;
import scrabble.model.Square;
import scrabble.model.SquareStar;


public class ScrabbleController{

   
    private static final int TILE_SIZE = 65;
    private static final int BOARD_SIZE = 15;

    private GameBoard gameBoard;

    @FXML
    private GridPane test;

    @FXML
    public void initialize() {
        gameBoard = new GameBoard();
        generateBoard();
    }

    private void generateBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                StackPane stack = new StackPane();
                Rectangle rect = new Rectangle();
                
                // Binding the size of the rectangle to the size of the GridPane cells
                rect.widthProperty().bind(this.test.widthProperty().divide(BOARD_SIZE));
                rect.heightProperty().bind(this.test.heightProperty().divide(BOARD_SIZE));

                Square square = gameBoard.getSquares()[row][col];

                // Set color based on special tiles
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

}
