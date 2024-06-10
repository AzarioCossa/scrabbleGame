package scrabble.model;

/**
 * The JokerTile class represents a special tile in the Scrabble game that can act as any letter.
 * It extends the Tile class and has additional functionality for setting and getting the letter it represents.
 */
public class JokerTile extends Tile {

    private static final long serialVersionUID = 1L;
    
    /**
     * The letter that this JokerTile represents.
     * Initially, it is null, meaning it has not been assigned a specific letter yet.
     */
    private FrenchLetters jokerLetter;

    /**
     * Constructs a new JokerTile with the default letter type set to JOCKER.
     */
    public JokerTile() {
        super(FrenchLetters.JOCKER);
        this.jokerLetter = null;
    }

    /**
     * Returns the letter that this JokerTile currently represents.
     * 
     * @return the letter that this JokerTile represents, or null if it has not been assigned a letter
     */
    public FrenchLetters getJokerLetter() {
        return jokerLetter;
    }

    /**
     * Sets the letter that this JokerTile should represent.
     * 
     * @param letter the letter to set
     * @return the newly set letter
     */
    public FrenchLetters setJockerLetter(FrenchLetters letter) {
        this.jokerLetter = letter;
        return this.jokerLetter;
    }
}
