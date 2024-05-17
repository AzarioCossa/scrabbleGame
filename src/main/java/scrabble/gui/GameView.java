package scrabble.gui;

import java.util.InputMismatchException;
import java.util.Scanner;

import scrabble.controller.GameController;
import scrabble.model.BoardSizeConstants;
import scrabble.model.GameBoard;
import scrabble.model.Position;
import scrabble.model.Rack;
import scrabble.model.Square;
import scrabble.model.Tile;

public class GameView {

	private static GameController gameController = new GameController("User");

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
	
	

	public static Position askPosition() {
		Position position = new Position();
		Boolean keyboardEntry = false;
		
		String rowEntry = null;
		String columnEntry = null;
		Scanner rowScanner = new Scanner(System.in);
		
		while (!keyboardEntry) {
			Console.messageBreak("What is the number of row where you want to play ? Select a number between 1 and 15 only !  ");
			rowEntry = rowScanner.nextLine();
			try {
				keyboardEntry = true;
				int row = Integer.parseInt(rowEntry);
				position.setRow(row);
			} catch (Exception e) {
				keyboardEntry = false;
				Console.messageBreak("Error you must enter an Integer !!!");
				Console.lineBreak();
				
			}
		}
		
		keyboardEntry = false;
		Scanner columnScanner = new Scanner(System.in);
		while (!keyboardEntry) {
			System.out.println("What is the number of column where you want to play ? Select a number between 1 and 15 only !  ");
			try {
				keyboardEntry = true;
				columnEntry = columnScanner.nextLine();
				int column = Integer.parseInt(columnEntry);
				position.setColumn(column);
			} catch (Exception e) {
				keyboardEntry = false;
				Console.messageBreak("Error you must enter an Integer !!!");
				Console.lineBreak();
			}
		}
		return position;
	}

	public static String askDirection() {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Where do you want to go from here ?(up, right, down ,left)  ");
		return keyboard.next();
	}

}
