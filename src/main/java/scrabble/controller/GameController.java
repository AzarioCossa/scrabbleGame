package scrabble.controller;

import java.util.ArrayList;

import scrabble.gui.Console;
import scrabble.gui.GameView;
import scrabble.model.Bag;
import scrabble.model.Direction;
import scrabble.model.GameBoard;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.User;
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

	public void exchangeTiles(Rack rack) {
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

	public User getUser() {
		return user;
	}

	public Bag getBag() {
		return bag;
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public Direction handleDirection(String directionChoice) {

		Direction userChoice = null;
		for (Direction direction : Direction.values()) {
			if (directionChoice == direction.getStringName()) {
				userChoice = direction;
			}
		}
		return userChoice;
	}

	public String validateDirection() {

		String directionChoice = GameView.askDirection();
		while (directionChoice.toLowerCase() != "up" || directionChoice.toLowerCase() != "right"
				|| directionChoice.toLowerCase() != "down" || directionChoice.toLowerCase() != "left") {
			Console.message("You can't choose another answer !");
			directionChoice = GameView.askDirection();

		}
		return directionChoice;
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
			if (letter == userChoice) {
				finalTile = tile;
			}
		}
		return finalTile;
	}

	public Boolean tileIsInRack(String tileLetter) {
		ArrayList<Tile> rack_content = new ArrayList<Tile>();
		rack_content.addAll(user.getRack().getTiles());

		for (Tile tile : rack_content) {
			String letter = tile.getLetter().toString();
			if (letter == tileLetter) {
				return true;
			}
		}
		Console.message("The choosen tile is not in the rack !");
		return false;

	}

	public void startGame() {
		GameView.printGrid(this.gameBoard);
		GameView.printRack(this.user.getRack());
	}

}
