package scrabble.application;

import scrabble.controller.GameController;
import scrabble.gui.GameView;


public class ScrabbleJeuxEssais {

	public static void main(String[] args) {
		GameController gameController = new GameController("User1");
		gameController.startGame();
        /*gameController.handleTile();
		gameController.handlePosition();
		gameController.handleDirection();
		gameController.handleTile();
		gameController.handlePosition();
		gameController.handleDirection();*/
		
//		int test = 0;
//		while(test != 10) {
//			gameController.placeTile();
//			gameController.putTileOfBagInRack(gameController.getUser().getRack());
//			GameView.printRack(gameController.getUser().getRack());
//			gameController.placeTile();
//			gameController.putTileOfBagInRack(gameController.getUser().getRack());
//			GameView.printRack(gameController.getUser().getRack());
//			
//			test++;
		}
		/*gameController.placeTile();
		gameController.putTileOfBagInRack(gameController.getUser().getRack());
		GameView.printRack(gameController.getUser().getRack());*/

//		System.out.println("Size before new draw : " + gameController.getUser().getRack().getTiles().size());
//		gameController.exchangeTiles(gameController.getUser().getRack());
//		System.out.println("Size after new draw : " + gameController.getUser().getRack().getTiles().size());
//		while (! gameController.getBag().getTiles().isEmpty()) {
//			gameController.exchangeTiles(gameController.getUser().getRack());
//		}
		//GameView.printRack(gameController.getUser().getRack());
		//gameController.putTileOfBagInRack(gameController.getUser().getRack());
		//GameView.printRack(gameController.getUser().getRack());
		
	}
	
