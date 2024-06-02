package scrabble.model.utils;

public class BagIsFullException extends Exception {
    public BagIsFullException(String message) {
        super(message);
    }
}