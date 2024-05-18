package scrabble.controller;

import java.util.ArrayList;
import java.util.Objects;

import scrabble.gui.Console;
import scrabble.gui.GameView;
import scrabble.model.Bag;
import scrabble.model.Direction;
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
		this.user = new User(name, initializeRack());
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

		} catch (EmptyBagException | RackIsFullException e)  {
			
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
			String letter = tile.getLetter().toString();
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
		GameView.printGrid(this.gameBoard);
		GameView.printRack(this.user.getRack());
	}
	

	
	public void placeTile() {
	    Tile tile = handleTile();
	    Position position = handlePosition();
	    System.out.println("Trying to place tile at position: " + position);

	    if (validatePosition(position) && (gameBoard.isEmpty(position))) {
	        System.out.println("Position is valid and empty. Placing tile...");
	        gameBoard.placeTile(tile, position.row(), position.column());
	        user.getRack().drawTile(tile);
	        GameView.printGrid(this.gameBoard);
	    } else {
	        System.out.println("Cannot place tile at position: " + position);
	    }
	}
	
	


	

}
