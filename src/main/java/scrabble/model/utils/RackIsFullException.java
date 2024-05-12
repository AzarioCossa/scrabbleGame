package scrabble.model.utils;

public class RackIsFullException extends Exception {
	public RackIsFullException(String message) {
		super(message);
	}
}
