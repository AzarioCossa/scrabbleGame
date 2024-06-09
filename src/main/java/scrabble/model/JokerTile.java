package scrabble.model;


public class JokerTile extends Tile {

	private static final long serialVersionUID = 1L;
    private FrenchLetters jokerLetter;

    public JokerTile() {
        super(FrenchLetters.JOCKER);
        this.jokerLetter = null;
    }

    public FrenchLetters getJokerLetter() {
        return jokerLetter;
    }
    public FrenchLetters setJockerLetter(FrenchLetters letter) {
    	this.jokerLetter =  letter;
    	return this.jokerLetter;
    	
    }

	

  
}
