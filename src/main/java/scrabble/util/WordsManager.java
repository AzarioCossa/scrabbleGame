package scrabble.util;

import java.util.Map;

import scrabble.model.BoardSizeConstants;
import scrabble.model.GameBoard;
import scrabble.model.Position;
import scrabble.model.Tile;

public class WordsManager {

	private GameBoard gameBoard;

	public WordsManager(GameBoard gameBoard) {
		super();
		this.gameBoard = gameBoard;
	}

	private boolean isAdjacentToExistingTile(Position position) {
		int row = position.row();
		int col = position.column();
		Position[] neighbors = { new Position(row - 1, col), new Position(row + 1, col), new Position(row, col - 1),
				new Position(row, col + 1) };

		for (Position neighbor : neighbors) {
			if (!gameBoard.isEmpty(neighbor)) {
				return true;
			}
		}
		return false;
	}

	public boolean validateWords(Map<Position, Tile> playedTiles) {
		boolean coversCenter = playedTiles.keySet().stream().anyMatch(
				position -> position.row() == BoardSizeConstants.MIDDLE_INDEX && position.column() == BoardSizeConstants.MIDDLE_INDEX);

		if (this.gameBoard.isEmpty() && !coversCenter) {
			return false;
		}

		boolean touchesExistingTile = playedTiles.keySet().stream().anyMatch(this::isAdjacentToExistingTile);
		if (this.gameBoard.isEmpty() || touchesExistingTile) {
			return true;
		}

		return false;
	}
}
