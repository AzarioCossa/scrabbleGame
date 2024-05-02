package scrabble.application;

import scrabble.controller.GameController;

public class ScrabbleApplicationConsole {
	private static String SEPARATOR = "---------------------------------------------------------";

	public static void main(String[] args) {
		System.out.println(SEPARATOR);
		System.out.println("--  Bienvenue dans notre magnifique jeu de Scrabble!  -- ");
		System.out.println("--  Développé par Azário Cossa                        -- ");
	  System.out.println("--  Et par Evan Gerbeaud                              -- ");

	  System.out.println("--  Et par Ilyas Boukhari                             --");

		System.out.println(SEPARATOR);
		
		GameController gameController = new GameController();
		
		gameController.startGame();

	}

}
