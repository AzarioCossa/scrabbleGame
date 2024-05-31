package scrabble.model;

public class Word {
    private String word;
    private Position startPosition;
    private Direction direction;

    public Word(String word, Position startPosition, Direction direction) {
        this.word = word;
        this.startPosition = startPosition;
        this.direction = direction;
    }

    // Getters
    public String getWord() {
        return word;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Direction getDirection() {
        return direction;
    }
}
