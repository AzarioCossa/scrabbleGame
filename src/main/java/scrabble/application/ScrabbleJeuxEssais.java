package scrabble.application;

import scrabble.controller.GameController;
import scrabble.gui.GameView;
import scrabble.model.Bag;
import scrabble.model.GameBoard;
import scrabble.model.User;

public class ScrabbleJeuxEssais {

	public static void main(String[] args) {

		Bag bag = new Bag();
		GameBoard gameBoard = new GameBoard(15, 15);
		User user = new User(bag, "Louis");

		GameController gameController = new GameController(bag, gameBoard, user);
		gameController.startGame();

		System.out.println("Size before new drax : " + user.getRack().getTiles().size());
		gameController.exchangeTiles(user.getRack());
		System.out.println("Size after new drax : " + user.getRack().getTiles().size());
		while (!bag.getTiles().isEmpty()) {
			gameController.exchangeTiles(user.getRack());
		}
		GameView.printRack(user.getRack());
		gameController.exchangeTiles(user.getRack());
		GameView.printRack(user.getRack());

	}

}
