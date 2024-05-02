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
    	Tile tile;
    	tile = this.bag.drawTile();
    	if (tile != null) {
    		rack.addTile(tile);
    		this.bag.shuffle();
    	}
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
    



}
