package scrabble.util;

import scrabble.model.BoardSizeConstants;
import scrabble.model.GameBoard;
import scrabble.model.Position;
import scrabble.model.Tile;

import java.util.Map;

public class WordsManager {

    private GameBoard gameBoard;

    public WordsManager(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean validateWords(Map<Position, Tile> tiles) {
    	return false;
    	/*
        if (gameBoard.isEmpty()) {
            if (tiles.size() < 2 || !passesThroughCenter(tiles)) {
                return false;
            }else {
            	return true;
            }
        }

        boolean isHorizontal = isWordHorizontal(tiles);
        boolean isVertical = isWordVertical(tiles);

        if (!isHorizontal && !isVertical) {
            return false;
        }

        for (Map.Entry<Position, Tile> entry : tiles.entrySet()) {
            Position pos = entry.getKey();
            Tile tile = entry.getValue();

            if (!gameBoard.isNotEmpty(pos)) {
                return false; // Vérification si la position est déjà occupée sur le plateau
            }

            if (isHorizontal) {
                if (pos.row() != tiles.keySet().iterator().next().row()) {
                    return false; // Vérification de l'horizontalité du mot
                }
            } else {
                if (pos.column() != tiles.keySet().iterator().next().column()) {
                    return false; // Vérification de la verticalité du mot
                }
            }
        }

        return true;
        */
    }

    private boolean isWordHorizontal(Map<Position, Tile> tiles) {
        int firstRow = -1;
        for (Position pos : tiles.keySet()) {
            if (firstRow == -1) {
                firstRow = pos.row();
            } else if (pos.row() != firstRow) {
                return false;
            }
        }
        return true;
    }

    private boolean isWordVertical(Map<Position, Tile> tiles) {
        int firstCol = -1;
        for (Position pos : tiles.keySet()) {
            if (firstCol == -1) {
                firstCol = pos.column();
            } else if (pos.column() != firstCol) {
                return false;
            }
        }
        return true;
    }

    private boolean passesThroughCenter(Map<Position, Tile> tiles) {
        int center = BoardSizeConstants.MIDDLE_INDEX;
        Position centerPosition = new Position(center, center);
        return tiles.containsKey(centerPosition);
    }
}
