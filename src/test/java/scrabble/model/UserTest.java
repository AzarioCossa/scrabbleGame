package scrabble.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The UserTest class contains unit tests for the User class.
 */
class UserTest {

    private User user;
    private Rack rack;
    private Word word1;
    private Word word2;

    /**
     * Sets up the user, rack, and words before each test.
     */
    @BeforeEach
    public void setUp() {
        rack = new Rack();
        user = new User("TestUser", rack);
    }

    /**
     * Tests the initial score of the user.
     */
    @Test
    void testInitialScore() {
        assertEquals(0, user.getScore());
    }

    /**
     * Tests the incrementScore method of the User class.
     *
     * @param increment the score increment to test
     */
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20})
    void testIncrementScore(int increment) {
        int initialScore = user.getScore();
        user.incrementScore(increment);
        assertEquals(initialScore + increment, user.getScore());
    }

    /**
     * Tests the addWord method of the User class.
     */
    @Test
    void testAddWord() {
        assertTrue(user.addWord(word1));
        assertTrue(user.addWord(word2));
    }

    /**
     * Tests the hasExchangedThisTurn method of the User class.
     */
    @Test
    void testHasExchangedThisTurn() {
        assertFalse(user.hasExchangedThisTurn());

        user.setHasExchangedThisTurn(true);
        assertTrue(user.hasExchangedThisTurn());

        user.setHasExchangedThisTurn(false);
        assertFalse(user.hasExchangedThisTurn());
    }

    /**
     * Tests the getRack method of the User class.
     */
    @Test
    void testGetRack() {
        assertEquals(rack, user.getRack());
    }

    /**
     * Tests the getName method of the User class.
     */
    @Test
    void testGetName() {
        assertEquals("TestUser", user.getName());
    }

}
