package scrabble.model.utils;

public class BagIsFullException extends Exception {
	private static final long serialVersionUID = 1L;

	public BagIsFullException(String message) {
		super(message);
	}
}

