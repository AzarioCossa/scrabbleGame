package scrabble.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Rack rack;
    private Word word1;
    private Word word2;

    @BeforeEach
    public void setUp() {
        rack = new Rack();
        user = new User("TestUser", rack);
    }

    @Test
    void testInitialScore() {
        assertEquals(0, user.getScore());
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20})
    void testIncrementScore(int increment) {
        int initialScore = user.getScore();
        user.incrementScore(increment);
        assertEquals(initialScore + increment, user.getScore());
    }

    @Test
    void testAddWord() {
        assertTrue(user.addWord(word1));
        assertTrue(user.addWord(word2));
    }

    @Test
    void testHasExchangedThisTurn() {
        assertFalse(user.hasExchangedThisTurn());

        user.setHasExchangedThisTurn(true);
        assertTrue(user.hasExchangedThisTurn());

        user.setHasExchangedThisTurn(false);
        assertFalse(user.hasExchangedThisTurn());
    }

    @Test
    void testGetRack() {
        assertEquals(rack, user.getRack());
    }

    @Test
    void testGetName() {
        assertEquals("TestUser", user.getName());
    }

}
