package scrabble.gui;

import scrabble.model.BoardSizeConstants;
import scrabble.model.GameBoard;
import scrabble.model.Rack;
import scrabble.model.Square;
import scrabble.model.Tile;

public class GameView {
    
    public static void printRack(Rack rack) {
        Console.title("Pièces disponibles:");
        Console.lineBreak();

        for (Tile tile : rack.getTiles()) {
            Console.message("[ " + tile.getLetter() + " ]");
            Console.lineBreak();
        }
        Console.lineBreak();

    }
    
    public static void printGrid(GameBoard gameBoard) {
        Square[][] squares = gameBoard.getSquares();
        int columns = BoardSizeConstants.DEFAULT_NUM_COLUMNS;
        int rows = BoardSizeConstants.DEFAULT_NUM_ROWS;

        for (int i = 0; i < columns; i++) {
        	Console.lineBreak();
            for (int j = 0; j < rows; j++) {
                Square square = squares[i][j];
                if (square.getTile() != null) {
                    Console.message(" | " + square.getTile().getLetter() + " | ");
                } else {
                    Console.message(" | " + square.getSymbol() + " | ");
                }
            }
            Console.lineBreak();
        }
    }
}
