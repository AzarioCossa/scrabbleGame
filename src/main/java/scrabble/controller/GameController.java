package scrabble.controller;

import java.util.ArrayList;
import java.util.List;

import scrabble.gui.GameView;
import scrabble.model.Bag;
import scrabble.model.GameBoard;
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
	public boolean exchangeTile(Rack rack, Tile tile) {
	    boolean tileFound = false;

	    for (Tile tileToDraw : rack.getTiles()) {
	        if (tileToDraw.getLetter() == tile.getLetter()) {
	            tileFound = true;
	            try {
	                // Retirer la tuile du rack
	                rack.drawTile(tileToDraw);

	                // Pioche une nouvelle tuile du sac
	                Tile tileToAdd = this.bag.drawTile();
	                if (tileToAdd != null) {
	                    rack.addTile(tileToAdd);
	                } 

	                // Ajoute la tuile échangée au sac
	                try {
	                    this.bag.addTile(tile);
	                } catch (BagIsFullException e) {
	                    System.out.println(e.getMessage());
	                }

	                // Mélange le sac
	                this.bag.shuffle();
	                
	            } catch (EmptyBagException e) {
	                System.out.println("The bag is empty: " + e.getMessage());
	            } catch (RackIsFullException e) {
	                System.out.println("The rack is full: " + e.getMessage());
	            }
	            break; 
	        }
	    }

	    if (!tileFound) {
	        System.out.println("The tile to exchange is not in the rack.");
	    }

	    return tileFound; 
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
