package scrabble.application;

import scrabble.controller.GameController;
import scrabble.gui.GameView;
import scrabble.model.utils.EmptyBagException;

public class ScrabbleJeuxEssais {

	public static void main(String[] args) {
		GameController gameController = new GameController("User1");
		gameController.startGame();

		System.out.println("Size before new draw : " + gameController.getUser().getRack().getTiles().size());
		gameController.exchangeTiles(gameController.getUser().getRack());
		System.out.println("Size after new draw : " + gameController.getUser().getRack().getTiles().size());
		while (! gameController.getBag().getTiles().isEmpty()) {
			try {
				gameController.getBag().drawTile();
			} catch (EmptyBagException e) {
				e.printStackTrace();
			}
		}
		GameView.printRack(gameController.getUser().getRack());
		gameController.exchangeTiles(gameController.getUser().getRack());
		GameView.printRack(gameController.getUser().getRack());

	}

}
