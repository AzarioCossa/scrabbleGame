package scrabble.model;

public class Word {
    private String word;
    private Position startPosition;
    private int point;

    public Word(String word, Position startPosition) {
        this.word = word;
        this.startPosition = startPosition;
    }

    // Getters
    public String getWord() {
        return word;
    }

    public Position getStartPosition() {
        return startPosition;
    }

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
