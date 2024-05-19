package scrabble.gui;

import java.util.Scanner;

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
    
    
    public static String askTile() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Which tile do you want to play ? ");
		return keyboard.nextLine();
	}
    
	public static String askRow() {
		Scanner rowScanner = new Scanner(System.in);
		Console.messageBreak("What is the number of row where you want to play ? Select a number between 1 and 15 only !  ");
		return rowScanner.nextLine();
	}
	
	public static String askColumn() {
		Scanner columnScanner = new Scanner(System.in);
		System.out.println("What is the number of column where you want to play ? Select a number between 1 and 15 only !  ");
		return columnScanner.nextLine();
	}
	
	public static String askDirection() {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Where do you want to go from here ?(up, right, down ,left)  ");
		return keyboard.next();
	}

	public static String askReplacementLetter() {
		Scanner jockerScanner = new Scanner(System.in);
	    System.out.println("Enter the letter you want the Joker to represent:");
	    return jockerScanner.nextLine().toUpperCase();
	}
	
	
}
