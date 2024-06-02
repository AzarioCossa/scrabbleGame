package scrabble.application;

import scrabble.controller.GameController;
import scrabble.gui.Console;
import scrabble.gui.GameView;
import scrabble.model.FrenchLetters;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.utils.EmptyBagException;
import scrabble.model.utils.RackIsFullException;

public class ScrabbleJeuxEssais {

	public static void main(String[] args) {
		GameController gameController = new GameController("User1");
	    gameController.startGame();
	    
	    Rack rack = new Rack();
	    
	    try {
	        rack.addTile(new Tile(FrenchLetters.A));
	        rack.addTile(new Tile(FrenchLetters.B));
	        rack.addTile(new Tile(FrenchLetters.C));
	    } catch (RackIsFullException e) {
	        System.out.println(e.getMessage());
	    }

	    Tile tileToExchange = new Tile(FrenchLetters.A);

	    System.out.println("rack before exchange :");
	    GameView.printRack(rack);
	    
	    boolean exchangeSuccessful = gameController.exchangeTile(rack, tileToExchange);
	    
	    if (exchangeSuccessful) {
	        System.out.println("rack after exchange :");
	        GameView.printRack(rack);
	    }
	    
	    Console.lineBreak();
	    
	    
	    
		System.out.println("Size before new draw : " + gameController.getUser().getRack().getTiles().size());
		gameController.putTileOfBagInRack(gameController.getUser().getRack());
		System.out.println("Size after new draw : " + gameController.getUser().getRack().getTiles().size());
		while (! gameController.getBag().getTiles().isEmpty()) {
			try {
				gameController.getBag().drawTile();
			} catch (EmptyBagException e) {
				e.printStackTrace();
			}
		}
		GameView.printRack(gameController.getUser().getRack());
		gameController.putTileOfBagInRack(gameController.getUser().getRack());
		GameView.printRack(gameController.getUser().getRack());
		
		
		
	}
}


