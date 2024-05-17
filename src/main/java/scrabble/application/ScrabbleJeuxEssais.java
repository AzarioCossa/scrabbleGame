package scrabble.application;

import scrabble.controller.GameController;
import scrabble.gui.GameView;

public class ScrabbleJeuxEssais {

	public static void main(String[] args) {
		GameController gameController = new GameController("User1");
		gameController.startGame();

		System.out.println("Size before new draw : " + gameController.getUser().getRack().getTiles().size());
		gameController.exchangeTiles(gameController.getUser().getRack());
		System.out.println("Size after new draw : " + gameController.getUser().getRack().getTiles().size());
//		while (! gameController.getBag().getTiles().isEmpty()) {
//			gameController.exchangeTiles(gameController.getUser().getRack());
//		}
		GameView.printRack(gameController.getUser().getRack());
		gameController.exchangeTiles(gameController.getUser().getRack());
		GameView.printRack(gameController.getUser().getRack());

	}

}
