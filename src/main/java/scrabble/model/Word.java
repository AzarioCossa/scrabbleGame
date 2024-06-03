package scrabble.model;

public class Word {
    private String word;
    private Position startPosition;

    public Word(String word, Position startPosition, Direction direction) {
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

}
