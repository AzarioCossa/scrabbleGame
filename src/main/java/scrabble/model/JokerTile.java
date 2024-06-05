package scrabble.model;

import java.util.Objects;

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
    public void setJockerLetter(FrenchLetters letter) {
    	this.jokerLetter =  letter;
    	
    }
    

    @Override
    public int getWeight() {
        return jokerLetter.getWeight();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(jokerLetter);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		JokerTile other = (JokerTile) obj;
		return jokerLetter == other.jokerLetter;
	}

  
}
