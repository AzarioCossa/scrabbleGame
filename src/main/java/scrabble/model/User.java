package scrabble.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The User class represents a player in the Scrabble game.
 * It contains information about the player's rack, name, score,
 * words played, and whether the player has exchanged tiles this turn.
 */
public class User {
    private Rack rack;
    private final String name;
    private final IntegerProperty score;
    private List<Word> words;
    private boolean hasExchangedThisTurn;

    /**
     * Constructs a User with the specified name and rack.
     * 
     * @param name the name of the user
     * @param rack the rack of the user
     */
    public User(String name, Rack rack) {
        this.name = name;
        this.rack = rack;
        this.score = new SimpleIntegerProperty(0);
        this.words = new ArrayList<>();
        this.hasExchangedThisTurn = false;
    }
    
    /**
     * Checks if the user has exchanged tiles this turn.
     * 
     * @return true if the user has exchanged tiles this turn, false otherwise
     */
    public boolean hasExchangedThisTurn() {
        return hasExchangedThisTurn;
    }

    /**
     * Sets whether the user has exchanged tiles this turn.
     * 
     * @param hasExchanged true if the user has exchanged tiles, false otherwise
     */
    public void setHasExchangedThisTurn(boolean hasExchanged) {
        this.hasExchangedThisTurn = hasExchanged;
    }

    /**
     * Adds a word to the list of words played by the user.
     * 
     * @param word the word to be added
     * @return true if the word was successfully added, false otherwise
     */
    public Boolean addWord(Word word) {
        return this.words.add(word);
    }

    /**
     * Returns the rack of the user.
     * 
     * @return the rack of the user
     */
    public Rack getRack() {
        return rack;
    }

    /**
     * Returns the name of the user.
     * 
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the score of the user.
     * 
     * @return the score of the user
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Increments the score of the user by the specified amount.
     * 
     * @param score the amount to increment the score by
     */
    public void incrementScore(int score) {
        this.score.set(this.score.get() + score);
    }

    /**
     * Returns the score property of the user.
     * 
     * @return the score property
     */
    public IntegerProperty scoreProperty() {
        return score;
    }
    
    /**
     * Returns the hash code value for the user.
     * 
     * @return the hash code value for the user
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, rack);
    }

    /**
     * Compares this user to the specified object for equality.
     * 
     * @param obj the object to be compared
     * @return true if the specified object is equal to this user, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(name, other.name) && Objects.equals(rack, other.rack);
    }
}
