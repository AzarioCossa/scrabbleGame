package scrabble.controller;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.gui.GameView;
import scrabble.model.Bag;
import scrabble.model.Direction;
import scrabble.model.FrenchLetters;
import scrabble.model.GameBoard;
import scrabble.model.Position;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.User;
import scrabble.model.utils.BagIsFullException;
import scrabble.model.utils.EmptyBagException;
import scrabble.model.utils.RackIsFullException;

public class GameController {
	private User user;
	private Bag bag;
	private GameBoard gameBoard;

	public GameController(String name) {
		this.bag = new Bag();
		this.gameBoard = new GameBoard();
	}

	public void putTileOfBagInRack(Rack rack) {
		Tile tile;
		try {
			tile = this.bag.drawTile();
			rack.addTile(tile);
			this.bag.shuffle();

		} catch (EmptyBagException e) {
			System.out.println(e.getMessage());
		} catch (RackIsFullException e) {
			System.out.println(e.getMessage());
		}
	}

	public void exchangeTile(Rack rack, Tile tile) {
		try {
			rack.drawTile(tile);
			Tile newTile = this.bag.drawTile();
			rack.addTile(newTile);

			try {
				this.bag.addTile(tile);
			} catch (BagIsFullException e) {
				System.out.println(e.getMessage());
			}
			this.bag.shuffle();
		} catch (EmptyBagException e) {
			System.out.println("The bag is empty: " + e.getMessage());
		} catch (RackIsFullException e) {
			System.out.println("The rack is full: " + e.getMessage());
		}
	}

	public Rack initializeRack() {
		Rack rack = new Rack();

		try {
			for (int i = 0; i < Rack.LIMIT_RACK_CAPACITY; i++) {
				rack.addTile(this.bag.drawTile());
			}

		} catch (EmptyBagException | RackIsFullException e) {

			System.out.println(e.getMessage());
		}
		return rack;

	}

	public Position handlePosition() {
		Position position = correctEntryPosition();
		while (!validatePosition(position)) {
			position = correctEntryPosition();
		}

		return position;
	}

	public Position correctEntryPosition() {
		String row;
		Integer rowInt = null;
		Boolean validateEntry = false;
		while (!validateEntry) {
			row = GameView.askRow();
			if (!validateEntryPositionAttribute(row)) {
				validateEntry = false;
			} else {
				rowInt = Integer.parseInt(row);
				if (!validateNumberRowColumn(rowInt)) {
					validateEntry = false;
				} else {
					validateEntry = true;
				}
			}

		}

		String column;
		Integer columnInt = null;
		validateEntry = false;
		while (!validateEntry) {
			column = GameView.askColumn();
			if (!validateEntryPositionAttribute(column)) {
				validateEntry = false;
			} else {
				columnInt = Integer.parseInt(column);
				if (!validateNumberRowColumn(columnInt)) {
					validateEntry = false;
				} else {
					validateEntry = true;
				}
			}

		}
		return new Position(rowInt, columnInt);
	}

	public Boolean validateEntryPositionAttribute(String entry) {
		Integer entryInt;
		try {
			entryInt = Integer.parseInt(entry);
			return true;
		} catch (Exception e) {
			Console.messageBreak("Error you must enter an Integer !!!");
			Console.lineBreak();
			return false;
		}
	}

	public Boolean validateNumberRowColumn(int number) {
		if (number < 1 || number > 15) {
			Console.messageBreak("You must enter a number between 1 and 15 ! ");
			Console.lineBreak();
			return false;
		} else {
			return true;
		}
	}

	public Boolean validatePosition(Position position) {
		if (!gameBoard.isEmpty(position)) {
			Console.messageBreak("The choosen square is already used !");
			Console.lineBreak();
			return false;
		} else {
			return true;
		}
	}

	public Direction handleDirection() {
		String directionChoice = GameView.askDirection();

		while (!directionChoice.toLowerCase().equals("up") && !directionChoice.toLowerCase().equals("right")
				&& !directionChoice.toLowerCase().equals("down") && !directionChoice.toLowerCase().equals("left")) {

			Console.messageBreak("You can't choose another answer !");
			Console.lineBreak();
			directionChoice = GameView.askDirection();
		}
		Direction userChoice = null;
		for (Direction direction : Direction.values()) {
			if (directionChoice.equals(direction.getStringName())) {
				userChoice = direction;
			}
		}
		return userChoice;
	}

	public Tile handleTile() {

		String userChoice = GameView.askTile();
		Tile finalTile = null;
		while (!tileIsInRack(userChoice)) {
			userChoice = GameView.askTile();
		}

		ArrayList<Tile> rack_content = new ArrayList<Tile>();
		rack_content.addAll(user.getRack().getTiles());
		for (Tile tile : rack_content) {
			String letter = tile.getLetter().toString().toLowerCase();
			if (Objects.equals(letter, userChoice.toLowerCase())) {
				finalTile = tile;
			}
		}
		return finalTile;
	}

	public Tile handleTileExchanged() {

		String userChoice = GameView.askTileExchanged();
		Tile finalTile = null;
		while (!tileIsInRack(userChoice)) {
			userChoice = GameView.askTile();
		}

		ArrayList<Tile> rack_content = new ArrayList<Tile>();
		rack_content.addAll(user.getRack().getTiles());
		for (Tile tile : rack_content) {
			String letter = tile.getLetter().toString().toLowerCase();
			if (Objects.equals(letter, userChoice)) {
				finalTile = tile;
			}
		}
		return finalTile;
	}

	public Boolean tileIsInRack(String tileLetter) {
		ArrayList<Tile> rackContent = new ArrayList<Tile>();
		rackContent.addAll(user.getRack().getTiles());
		tileLetter = tileLetter.toLowerCase();

		for (Tile tile : rackContent) {
			String letter = tile.getLetter().toString().toLowerCase();
			if (letter.equals(tileLetter)) {
				return true;
			}
		}
		Console.messageBreak("The choosen tile is not in the rack !");
		Console.lineBreak();
		return false;
	}

	public User getUser() {
		return user;
	}

	public Bag getBag() {
		return bag;
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public void startGame() {
		Scanner keyboard = new Scanner(System.in);
		Console.message("Welcome to Scrabble !  ");
		Console.messageBreak("What's your name ?");
		String userName = keyboard.nextLine();
		String userChoice;
		this.user = new User(userName, initializeRack());
		Console.messageBreak("Welcome " + userName + "!");

		Console.messageBreak("During the game, a menu will be displayed to allow you to choose what you want to do \n");
		Console.messageBreak("Differens choices are :\n-1: Place tile\n-2: Exchange tile\n-3: Leave the game\n");
		GameView.printGrid(this.gameBoard);
		GameView.printRack(this.user.getRack());

		Boolean endGame = false;
		while (!endGame) {
			GameView.printGrid(this.gameBoard);
			GameView.printRack(this.user.getRack());
			Console.messageBreak("What do you want to do ?:\n-1: Place tile\n-2: Exchange tile\n-3: Leave the game\n");
			userChoice = keyboard.nextLine();
			while (!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3")) {
				Console.messageBreak("You have to enter numbers 1,2 or 3 !");
				userChoice = keyboard.nextLine();
			}
			if (userChoice.equals("1")) {
				placeTile();
				putTileOfBagInRack(user.getRack());
			} else {
				if (userChoice.equals("2")) {
					Console.messageBreak("Which tile of your rack would you exchange ? ");
					Tile tile = handleTileExchanged();
					exchangeTile(user.getRack(), tile);
				} else {
					if (userChoice.equals("3")) {
						Console.messageBreak("Thanks for your trust see you soon !");
						endGame = true;
					}

				}
			}
		}

	}

	public void placeTile() {
		Tile tile = handleTile();
		Position center = new Position(8, 8);
		if (gameBoard.isEmpty(center)) {
			if (tile.getLetter() == FrenchLetters.JOCKER) {
				String replacementLetter = GameView.askReplacementLetter();
				while (!validateEntryReplacementJocker(replacementLetter)) {
					GameView.askReplacementLetter();
					Console.messageBreak("Alphabetical character expected !");
				}
				user.getRack().replaceJoker(tile);
			}

			gameBoard.placeTileGameBoard(tile, center.row() - 1, center.column() - 1);
			user.getRack().drawTile(tile);
		} else {
			Position position = handlePosition();
			while (!isAdjacent(position)) {
				Console.messageBreak("Error, tile must be placed next to an other ! ");
				position = handlePosition();
			}

			if (tile.getLetter() == FrenchLetters.JOCKER) {
				String replacementLetter = GameView.askReplacementLetter();
				while (!validateEntryReplacementJocker(replacementLetter)) {
					GameView.askReplacementLetter();
					Console.messageBreak("Alphabetical character expected !");
				}
				user.getRack().replaceJoker(tile);
			}

			gameBoard.placeTileGameBoard(tile, position.row() - 1, position.column() - 1);
			user.getRack().drawTile(tile);
		}
	}

	public Boolean isAdjacent(Position position) {
		int row = position.row();
		int column = position.column();
		Position left = new Position(row, column - 1);
		Position right = new Position(row, column + 1);
		Position top = new Position(row - 1, column);
		Position bottom = new Position(row + 1, column);

		if (isTopLeftSquare(row, column)) {
			if (gameBoard.isNotEmpty(bottom) || gameBoard.isNotEmpty(right)) {
				return true;
			}}
		else if (isBottomRightSquare(row, column)) {
				if (gameBoard.isNotEmpty(top) || gameBoard.isNotEmpty(left)) {
					return true;
				}
			} else if (isTopRightSquare(row, column)) {
				if (gameBoard.isNotEmpty(bottom) || gameBoard.isNotEmpty(left)) {
					return true;
				}
			} else if (isBottomLeftSquare(row, column)) {
				if (gameBoard.isNotEmpty(top) || gameBoard.isNotEmpty(right)) {
					return true;
				}
			}
				else { 
					if (gameBoard.isNotEmpty(top) || gameBoard.isNotEmpty(right) || gameBoard.isNotEmpty(left) || gameBoard.isNotEmpty(bottom)) {
						return true;
					}
			}	
			return false;
			}	

	private boolean isBottomLeftSquare(int row, int column) {
		return row == 15 && column == 1;
	}

	private boolean isTopRightSquare(int row, int column) {
		return row == 1 && column == 15;
	}

	private boolean isBottomRightSquare(int row, int column) {
		return row == 15 && column == 15;
	}

	private boolean isTopLeftSquare(int row, int column) {
		return row == 1 && column == 1;
	}

	public Boolean validateEntryReplacementJocker(String answer) {
		String[] number = { "0", "1", "2", "3", "4,", "5", "6", "7", "8", "9" };

		if (answer.length() != 1) {
			return false;
		}

		Boolean find = true;
		for (String element : number) {
			if (element.equals(answer)) {
				find = false;
			}
		}

		return find;
	}
}
