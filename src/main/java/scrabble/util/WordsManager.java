package scrabble.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import scrabble.model.BoardSizeConstants;
import scrabble.model.GameBoard;
import scrabble.model.Position;
import scrabble.model.Tile;

public class WordsManager {

    private GameBoard gameBoard;

    public WordsManager(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean validateWords(Map<Position, Tile> tiles) {
    	return true;
    	/*
        if (gameBoard.isEmpty()) {
            if (tiles.size() < 2 || !passesThroughCenter(tiles)) {
                return false;
            }
        }

        boolean isHorizontal = isWordHorizontal(tiles);
        boolean isVertical = isWordVertical(tiles);

        if (!isHorizontal && !isVertical) {
            return false;
        }

        List<Position> sortedPositions = sortPositions(tiles.keySet(), isHorizontal);
        return isContinuousAndConnected(sortedPositions, isHorizontal);
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
        */
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

    private List<Position> sortPositions(Iterable<Position> positions, boolean isHorizontal) {
        List<Position> sortedPositions = new ArrayList<>();
        for (Position pos : positions) {
            sortedPositions.add(pos);
        }

        Collections.sort(sortedPositions, new Comparator<Position>() {
            @Override
            public int compare(Position p1, Position p2) {
                if (isHorizontal) {
                    return Integer.compare(p1.column(), p2.column());
                } else {
                    return Integer.compare(p1.row(), p2.row());
                }
            }
        });

        return sortedPositions;
    }

    private boolean isContinuousAndConnected(List<Position> sortedPositions, boolean isHorizontal) {
        boolean isConnected = false;

        if (isHorizontal) {
            int row = sortedPositions.get(0).row();
            int minCol = sortedPositions.get(0).column();
            int maxCol = sortedPositions.get(sortedPositions.size() - 1).column();

            for (int col = minCol; col <= maxCol; col++) {
                Position pos = new Position(row, col);
                if (!sortedPositions.contains(pos) && gameBoard.getSquares()[row][col].getTile() == null) {
                    return false;
                }
                if (gameBoard.getSquares()[row][col].getTile() != null) {
                    isConnected = true;
                }
            }
        } else {
            int col = sortedPositions.get(0).column();
            int minRow = sortedPositions.get(0).row();
            int maxRow = sortedPositions.get(sortedPositions.size() - 1).row();

            for (int row = minRow; row <= maxRow; row++) {
                Position pos = new Position(row, col);
                if (!sortedPositions.contains(pos) && gameBoard.getSquares()[row][col].getTile() == null) {
                    return false;
                }
                if (gameBoard.getSquares()[row][col].getTile() != null) {
                    isConnected = true;
                }
            }
        }

        return isConnected;
    }

    private boolean passesThroughCenter(Map<Position, Tile> tiles) {
        int center = BoardSizeConstants.MIDDLE_INDEX;
        Position centerPosition = new Position(center, center);
        return tiles.containsKey(centerPosition);
    }
}
