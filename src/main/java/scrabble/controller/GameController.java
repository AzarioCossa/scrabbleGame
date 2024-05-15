package scrabble.controller;

import java.util.ArrayList;

import scrabble.gui.GameView;
import scrabble.model.Bag;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.GameBoard;
import scrabble.model.User;
import scrabble.model.utils.EmptyBagException;
import scrabble.model.utils.RackIsFullException;

public class GameController {
	private User user;
	private Bag bag;
	private GameBoard gameBoard;

	public GameController(String name) {
		this.bag = new Bag();
		this.gameBoard = new GameBoard(15, 15);
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
	

//	public Rack initializeRack() {
//		Rack rack = new Rack();
//
//		try {
//			for (int i = 0; i < Rack.LIMIT_RACK_CAPACITY; i++) {
//				rack.addTile(this.bag.drawTile());
//			}
//
//		} catch (EmptyBagException  | RackIsFullException e) {
//			
//			System.out.println(e.getMessage());
//		}
//		return rack;
//
//	}
	
	
	public Rack initializeRack() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		Rack rack = new Rack();
		rack.setTiles(tiles);

		try {
			for (int i = 0; i < Rack.LIMIT_RACK_CAPACITY; i++) {
				rack.addTile(this.bag.drawTile());
			}

		} catch (EmptyBagException | RackIsFullException e)  {
			
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

	public void startGame() {
		GameView.printGrid(this.gameBoard);
		GameView.printRack(this.user.getRack());
	}
	
	

}
