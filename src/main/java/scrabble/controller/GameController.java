package scrabble.controller;

import java.util.ArrayList;


import scrabble.gui.GameView;
import scrabble.model.Bag;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.model.GameBoard;
import scrabble.model.User;

public class GameController {
    private User user;
    private Bag bag;
    private GameBoard gameBoard;
   
    
    public GameController() {
        this.bag = new Bag();
        this.gameBoard = new GameBoard(15, 15);
        Rack rack = new Rack(initializeRack());
        this.user = new User(rack,"Louis");
    }


    public void exchangeTiles(Rack rack) {
        rack.addTile(this.bag.getTiles().get(0));
        this.bag.drawTile();
        this.bag.shuffle();
    }

    public ArrayList<Tile> initializeRack() {
    	ArrayList<Tile> tile = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            tile.add(this.bag.drawTile());
        }
        return tile;

    }
    
    public void startGame() {
    	
    	GameView.printGrid(this.gameBoard);
    	GameView.printRack(this.user.getRack());

    	
    }
    
    

 
/*
    public int[] askPosition() {
        try (Scanner input = new Scanner(System.in)) {
            int[] position = new int[2];
            Utilities.text("Entrez la position de la lettre que vous voulez mettre");
            Utilities.text("Ligne   : ");
            position[0] = input.nextInt();
            Utilities.text("Colonne : ");
            position[1] = input.nextInt();

            return position;
        }
    }

    public void placeTile(int[] position, Tile tile) {
        gameBoard.getSquares()[position[0]][position[1]].placeTile(tile);
    }
    */


}
