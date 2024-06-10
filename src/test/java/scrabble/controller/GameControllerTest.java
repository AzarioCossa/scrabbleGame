package scrabble.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Rack;

class GameControllerTest {

	private GameController gameController;
	
	@BeforeEach
    public void setUp() {
        gameController = new GameController("Test");
    }
	
	@Test
    void testRackInitialization() {
		Rack rack = gameController.initializeRack();
        assertEquals(7, rack.getTiles().size());
    }
	

}
