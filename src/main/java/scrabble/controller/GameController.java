package scrabble.controller;

import java.util.ArrayList;

import scrabble.gui.GameView;
import scrabble.model.Bag;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.GameBoard;
import scrabble.model.User;
import scrabble.model.utils.EmptyBagException;

public class GameController {
	private User user;
	private Bag bag;
	private GameBoard gameBoard;

	public GameController(Bag bag, GameBoard gameBoard, User user) {
		this.bag = bag;
		this.gameBoard = gameBoard;
		this.user = user;
	}

	public void exchangeTiles(Rack rack) {
		Tile tile;
		try {
			tile = this.bag.drawTile();
			rack.addTile(tile);
			this.bag.shuffle();
			
		} catch (EmptyBagException e) {
			System.out.println(e.getMessage());

		}

	}

	public void startGame() {
		GameView.printGrid(this.gameBoard);
		GameView.printRack(this.user.getRack());
	}

}
