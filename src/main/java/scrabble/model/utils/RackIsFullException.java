package scrabble.model.utils;

public class RackIsFullException extends Exception {
	private static final long serialVersionUID = 1L;

	public RackIsFullException(String message) {
		super(message);
	}
}
