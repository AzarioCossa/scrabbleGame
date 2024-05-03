package scrabble.application;

import scrabble.controller.GameController;
import scrabble.model.Bag;
import scrabble.model.GameBoard;
import scrabble.model.User;

public class ScrabbleApplicationConsole {
	private static String SEPARATOR = "---------------------------------------------------------";

	public static void main(String[] args) {
		System.out.println(SEPARATOR);
		System.out.println("--  Bienvenue dans notre magnifique jeu de Scrabble!  -- ");
		System.out.println("--  Développé par Azário Cossa                        -- ");
		System.out.println("--  Et par Evan Gerbeaud                              -- ");

		System.out.println("--  Et par Ilyas Boukhari                             --");

		System.out.println(SEPARATOR);
		Bag bag = new Bag();
		GameBoard gameBoard = new GameBoard(15, 15);
		User user = new User(bag, "Louis");

		GameController gameController = new GameController(bag, gameBoard, user);
		gameController.startGame();


	}

}
