package scrabble.controller;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.gui.GameView;
import scrabble.model.Bag;
import scrabble.model.BoardSizeConstants;
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
		while (!positionIsUnused(position)) {
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
			if (!EntryPositionAttributeIsInt(row)) {
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
			if (!EntryPositionAttributeIsInt(column)) {
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

	public Boolean EntryPositionAttributeIsInt(String entry) {
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

	public Boolean positionIsUnused(Position position) {
		if (!gameBoard.isEmpty(position)) {
			Console.messageBreak("The choosen square is already used !");
			Console.lineBreak();
			return false;
		} else {
			return true;
		}
	}

	public Direction handleDirection() {
		String directionChoice = GameView.askDirection().toLowerCase();

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

	public Boolean wordIsTooLongForDirection(String word, Position position, Direction direction) {

		String error = "Your word is too long for this direction !";
		int row = position.row();
		int column = position.column();
		Position copy = new Position(row, column);
		for (int i = 0; i < word.length() - 1; i++) {
			switch (direction) {

			case UP:
				if (copy.row() < 1) {
					Console.messageBreak(error);
					return false;
				} else {
					copy.setRow(copy.row() - 1);
					break;
				}

			case DOWN:
				if (copy.row() > 15) {
					Console.messageBreak(error);
					return false;
				} else {
					copy.setRow(copy.row() + 1);
					break;
				}

			case LEFT:
				if (copy.column() < 1) {
					Console.messageBreak(error);
					return false;
				} else {
					copy.setColumn(copy.column() - 1);
					break;
				}

			case RIGHT:
				if (copy.column() > 15) {
					Console.messageBreak(error);
					return false;
				} else {
					copy.setColumn(copy.column() + 1);
					break;
				}

			}
		}
		if (copy.row() < 1 || copy.row() > 15 || copy.column() < 1 || copy.column() > 15) {
			Console.messageBreak(error);
			return false;
		} else {

			return true;
		}
	}

	public Tile handleTile() {

		String userChoice = GameView.askTile();
		Tile finalTile = null;
		while (!tileIsInRack(userChoice, user.getRack())) {
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
		while (!tileIsInRack(userChoice, user.getRack())) {
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

	public Boolean tileIsInRack(String tileLetter, Rack rack) {
		ArrayList<Tile> rackContent = new ArrayList<Tile>();
		rackContent.addAll(rack.getTiles());
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

	public Boolean wordInRack(String word) {
		ArrayList<String> arrayWord = new ArrayList<>();
		Boolean inRack = false;
		for (int i = 0; i < word.length(); i++) {
			arrayWord.add(String.valueOf(word.charAt(i)));
		}
		Rack rack = new Rack();
		for (Tile tile : user.getRack().getTiles()) {
			try {
				rack.addTile(tile);
			} catch (RackIsFullException e) {
				Console.message(null);
			}
		}

		for (String letter : arrayWord) {
			if (!tileIsInRack(letter, rack)) {
				Console.messageBreak("One of tile wanted is not in the rack !!");
				return false;
			} else {
				for (int i = 0; i < rack.getTiles().size(); i++) {
					String tileLetter = rack.getTiles().get(i).getLetter().toString().toLowerCase();
					if (tileLetter.equals(letter)) {
						rack.drawTile(rack.getTiles().get(i));
						break;
					}
				}

			}

		}

		return true;
	}

	public Boolean wordIsAdjacent(String word, Position position, Direction direction) {

		Position copy = new Position(position.row(), position.column());
		for (int i = 0; i < word.length(); i++) {
			if (isAdjacent(copy)) {
				return true;
			}

			switch (direction) {

			case UP:
				copy.setRow(copy.row() - 1);
				break;

			case DOWN:
				copy.setRow(copy.row() + 1);
				break;

			case LEFT:
				copy.setColumn(copy.column() - 1);
				break;

			case RIGHT:
				copy.setColumn(copy.column() + 1);
				break;
			}
		}
		Console.messageBreak("Error, your word must be placed next to a tile ! ");
		return false;
	}

	public String handleWord() {
		String word = GameView.askWord().toLowerCase();

		while (!wordInRack(word) || !wordiIsLongEnought(word)) {
			word = GameView.askWord().toLowerCase();
		}

		return word;
	}

	public Boolean wordiIsLongEnought(String word) {
		if (word.length() < 2) {
			Console.messageBreak("A word must contain at least 2 letters");
			return false;
		} else {

			return true;
		}
	}

	public boolean tileOnTheRoad(Position position, Direction direction) {
		int row = position.row();
		int column = position.column();
		Position nextPosition = new Position(row, column);
		switch (direction) {
		case UP:
			nextPosition.setRow(position.row() - 1);
			if (gameBoard.isNotEmpty(nextPosition)) {
				return true;
			} else {
				return false;
			}

		case DOWN:
			nextPosition.setRow(position.row() + 1);
			if (gameBoard.isNotEmpty(nextPosition)) {
				return true;
			} else {
				return false;
			}
		case RIGHT:
			nextPosition.setColumn(position.column() + 1);
			if (gameBoard.isNotEmpty(nextPosition)) {
				return true;
			} else {
				return false;
			}
		case LEFT:
			nextPosition.setColumn(position.column() - 1);
			if (gameBoard.isNotEmpty(nextPosition)) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	public int calculateGap(Position position, Direction direction) {
		int gap = 0;
		Position nextPosition = new Position(position.row(), position.column());

		while (tileOnTheRoad(nextPosition, direction)) {
			gap++;

			switch (direction) {

			case UP:
				nextPosition.setRow(nextPosition.row() - 1);
				break;

			case DOWN:
				nextPosition.setRow(nextPosition.row() + 1);
				break;

			case LEFT:
				nextPosition.setColumn(nextPosition.column() - 1);
				break;

			case RIGHT:
				nextPosition.setColumn(nextPosition.column() + 1);
				break;
			}
		}
		return gap;
	}

	public void placeWord(String word) {
		Position center = new Position(8, 8);
		int gap;

		ArrayList<String> arrayWord = new ArrayList<>();

		for (int i = 0; i < word.length(); i++) {
			arrayWord.add(String.valueOf(word.charAt(i)));
		}

		if (gameBoard.isEmpty(center)) {
			Direction direction = handleDirection();
			for (String letter : arrayWord) {
				placeTileOfWord(letter, center);

				switch (direction) {

				case UP:
					center.setRow(center.row() - 1);
					break;

				case DOWN:
					center.setRow(center.row() + 1);
					break;

				case LEFT:
					center.setColumn(center.column() - 1);
					break;

				case RIGHT:
					center.setColumn(center.column() + 1);

				}
			}
		} else {
			Position position = handlePosition();
			Direction direction = handleDirection();
			while (!wordIsAdjacent(word, position, direction)
					|| !wordIsTooLongForDirection(word, position, direction)) {
				position = handlePosition();
				direction = handleDirection();
			}
			Position nextPosition = new Position(position.row(), position.column());
			for (String letter : arrayWord) {
				gap = calculateGap(nextPosition, direction);
				placeTileOfWord(letter, nextPosition);

				switch (direction) {

				case UP:
					nextPosition.setRow(nextPosition.row() - 1);
					nextPosition.setRow(nextPosition.row() - gap);
					break;

				case DOWN:
					nextPosition.setRow(nextPosition.row() + 1);
					nextPosition.setRow(nextPosition.row() + gap);
					break;

				case LEFT:
					nextPosition.setColumn(nextPosition.column() - 1);
					nextPosition.setColumn(nextPosition.column() - gap);
					break;

				case RIGHT:
					nextPosition.setColumn(nextPosition.column() + 1);
					nextPosition.setColumn(nextPosition.column() + gap);
					break;
				}

			}
		}
	}

	public void placeTileOfWord(String letter, Position position) {
		ArrayList<Tile> rack_content = new ArrayList<Tile>();
		rack_content.addAll(user.getRack().getTiles());
		for (Tile tile : rack_content) {
			if (letter.toUpperCase().equals(tile.getLetter().toString().toUpperCase())) {
				gameBoard.placeTileGameBoard(tile, position.row() - 1, position.column() - 1);
				user.getRack().drawTile(tile);
				break;
			}
		}
	}

	public void removePlacedTilesFromRack(String word) {
		Rack rack = user.getRack();
		ArrayList<Tile> tilesToRemove = new ArrayList<>();

		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			String letterAsString = String.valueOf(letter);

			for (Tile tile : rack.getTiles()) {
				if (tile.getLetter().toString().equals(letterAsString)) {

					tilesToRemove.add(tile);
					break;
				}
			}
		}

		for (Tile tile : tilesToRemove) {
			rack.drawTile(tile);
		}
	}

	public void placeTile(Tile tile) {

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
			user.setScore(tile.getWeight());
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
			user.setScore(tile.getWeight());
			user.getRack().drawTile(tile);
		}
	}

	public int calculatePointWord(String word) {
		ArrayList<String> arrayWord = new ArrayList<>();
		ArrayList<Tile> rack_content = new ArrayList<Tile>();
		rack_content.addAll(user.getRack().getTiles());
		int point = 0;

		for (int i = 0; i < word.length(); i++) {
			arrayWord.add(String.valueOf(word.charAt(i)));
		}

		for (String letter : arrayWord) {
			for (Tile tile : rack_content) {
				if (letter.toLowerCase().equals(tile.getLetter().toString().toLowerCase())) {
					point = point + tile.getWeight();
				}
			}
		}
		return point;
	}

	public Boolean isAdjacentHorizontally(Position position) {
		Position nextPosition = new Position(position.row(), position.column() + 1);
		if (gameBoard.isEmpty(nextPosition)) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean isAdjacentVertically(Position position) {
		Position nextPosition = new Position(position.row() + 1, position.column());
		if (gameBoard.isEmpty(nextPosition)) {
			return false;
		} else {
			return true;
		}
	}

	public int calculateTotalPoint() {
		int point = 0;
		Position start = new Position(1, 1);

		for (int i = 0; i < BoardSizeConstants.DEFAULT_NUM_ROW - 1; i++) {
			int j = 0;
			while (j < BoardSizeConstants.DEFAULT_NUM_COLUMNS - 1) {

				if (gameBoard.isNotEmpty(start) && isAdjacentHorizontally(start)) {
					while (isAdjacentHorizontally(start)) {
						point += gameBoard.square(start).getTile().getWeight();
						start.setColumn(start.column() + 1);
						j++;
					}
					point += gameBoard.square(start).getTile().getWeight();
					start.setColumn(start.column() + 1);
					j++;
				} else {
					start.setColumn(start.column() + 1);
					j++;
				}
			}
			start.setRow(start.row() + 1);
			start.setColumn(1);
		}
		start.setRow(1);
		start.setColumn(1);
		for (int i = 0; i < BoardSizeConstants.DEFAULT_NUM_COLUMNS - 1; i++) {
			int j = 0;
			while (j < BoardSizeConstants.DEFAULT_NUM_ROW - 1) {

				if (gameBoard.isNotEmpty(start) && isAdjacentVertically(start)) {
					while (isAdjacentVertically(start)) {
						point += gameBoard.square(start).getTile().getWeight();
						start.setRow(start.row()+1);
						j++;
					}
					point += gameBoard.square(start).getTile().getWeight();
					start.setRow(start.row()+1);
					j++;
				} else {
					start.setRow(start.row()+1);
					j++;
				}
			}
			start.setColumn(start.column() +1);
			start.setRow(1);
		} 

		return point;
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
			}
		} else if (isBottomRightSquare(row, column)) {
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
		} else if (isOnTopOfTheBoard(row)) {
			if (gameBoard.isNotEmpty(left) || gameBoard.isNotEmpty(right) || gameBoard.isNotEmpty(bottom)) {
				return true;
			}
		} else if (isOnLeftOfTheBoard(column)) {
			if (gameBoard.isNotEmpty(top) || gameBoard.isNotEmpty(right) || gameBoard.isNotEmpty(bottom)) {
				return true;
			}
		} else if (isOnRightOfTheBoard(column)) {
			if (gameBoard.isNotEmpty(top) || gameBoard.isNotEmpty(left) || gameBoard.isNotEmpty(bottom)) {
				return true;
			}
		} else if (isOnBottomOfBoard(row)) {
			if (gameBoard.isNotEmpty(top) || gameBoard.isNotEmpty(right) || gameBoard.isNotEmpty(left)) {
				return true;
			}
		} else {
			if (gameBoard.isNotEmpty(top) || gameBoard.isNotEmpty(right) || gameBoard.isNotEmpty(left)
					|| gameBoard.isNotEmpty(bottom)) {
				return true;
			}
		}
		return false;
	}

	public void startGame() {
		Position center = new Position(8, 8);
		Scanner keyboard = new Scanner(System.in);
		Console.message("Welcome to Scrabble !  ");
		Console.messageBreak("What's your name ?");
		String userName = keyboard.nextLine();
		String userChoice;
		this.user = new User(userName, initializeRack());
		Console.messageBreak("Welcome " + userName + "!");

		Console.messageBreak("During the game, a menu will be displayed to allow you to choose what you want to do \n");
		Console.messageBreak(
				"Differens choices are :\n-1: Place tile\n-2: Exchange tile\n-3: PLace Word\n-4: Leave the game\n");

		Boolean endGame = false;
		while (!endGame) {
			GameView.printGrid(this.gameBoard);
			GameView.printRack(this.user.getRack());
			user.setScore(calculateTotalPoint());
			Console.messageBreak("Your score : " + user.getScore() + "\n");
			Console.messageBreak(
					"What do you want to do ?:\n-1: Place tile\n-2: Exchange tile\n-3: PLace Word\n-4: Leave the game");
			userChoice = keyboard.nextLine();
			while (!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3")
					&& !userChoice.equals("4")) {
				Console.messageBreak("You have to enter numbers 1, 2, 3 or 4 !");
				userChoice = keyboard.nextLine();
			}
			if (userChoice.equals("1")) {
				Tile tile = handleTile();
				placeTile(tile);
				putTileOfBagInRack(user.getRack());
			} else {
				if (userChoice.equals("2")) {
					Console.messageBreak("Which tile of your rack would you exchange ? ");
					Tile tile = handleTileExchanged();
					exchangeTile(user.getRack(), tile);
				} else {
					if (userChoice.equals("3")) {
						String word = handleWord();
						placeWord(word);
						for (int i = 0; i < word.length(); i++) {
							putTileOfBagInRack(user.getRack());
						}

					} else {
						if (userChoice.equals("4")) {
							Console.messageBreak("Thanks for your trust see you soon !");
							endGame = true;

						}
					}
				}
			}
		}
	}

	private boolean isOnBottomOfBoard(int row) {
		return row == 15;
	}

	private boolean isOnRightOfTheBoard(int column) {
		return column == 15;
	}

	private boolean isOnLeftOfTheBoard(int column) {
		return column == 1;
	}

	private boolean isOnTopOfTheBoard(int row) {
		return row == 1;
	}

	private boolean isBottomLeftSquare(int row, int column) {
		return isOnBottomOfBoard(row) && isOnLeftOfTheBoard(column);
	}

	private boolean isTopRightSquare(int row, int column) {
		return isOnTopOfTheBoard(row) && isOnRightOfTheBoard(column);
	}

	private boolean isBottomRightSquare(int row, int column) {
		return isOnBottomOfBoard(row) && isOnRightOfTheBoard(column);
	}

	private boolean isTopLeftSquare(int row, int column) {
		return isOnTopOfTheBoard(row) && isOnLeftOfTheBoard(column);
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
