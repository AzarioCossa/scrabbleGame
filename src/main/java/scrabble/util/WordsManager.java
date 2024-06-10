package scrabble.util;

import scrabble.model.*;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;

public class WordsManager {

    private final GameBoard gameBoard;

    public WordsManager(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Calculate the score of a word based on the board configuration.
     *
     * @param word the word to calculate the score for
     * @return the calculated score of the word
     */
    public int calculateWordScore(Word word) {
        int wordScore = 0;
        int wordMultiplier = 1;

        for (Map.Entry<Position, Tile> entry : word.getTiles().entrySet()) {
            Position position = entry.getKey();
            Tile tile = entry.getValue();

            int letterMultiplier = 1;

            Square square = gameBoard.getSquares()[position.row() - 1][position.column() - 1];
            SquareType bonus = square.getSquareType();

            switch (bonus) {
                case DOUBLE_LETTER:
                    letterMultiplier = 2;
                    break;
                case TRIPLE_LETTER:
                    letterMultiplier = 3;
                    break;
                case DOUBLE_WORD:
                    wordMultiplier *= 2;
                    break;
                case TRIPLE_WORD:
                    wordMultiplier *= 3;
                    break;
                default:
                    break;
            }

            if (tile != null) {
            wordScore += tile.getWeight() * letterMultiplier;
            }
        }

        wordScore *= wordMultiplier;

        return wordScore;
    }

    public boolean validateWords(Map<Position, Tile> tiles) {
        if (Boolean.TRUE.equals(gameBoard.isEmpty())) {
            return validateFirstWord(tiles);
        } else {
            return validateConnectedWord(tiles);
        }
    }

    private boolean validateFirstWord(Map<Position, Tile> tiles) {
        if (tiles.size() < 2 || !passesThroughCenter(tiles) || !isWordAligned(tiles)) {
            return false;
        }
        return true;
    }

    private boolean validateConnectedWord(Map<Position, Tile> tiles) {
        if (!isWordAligned(tiles) || !isWordConnected(tiles)) {
            return false;
        }
        return true;
    }

    private boolean passesThroughCenter(Map<Position, Tile> tiles) {
        Position centerPosition = new Position(BoardSizeConstants.MIDDLE_INDEX, BoardSizeConstants.MIDDLE_INDEX);
        return tiles.keySet().contains(centerPosition);
    }


    private boolean isWordAligned(Map<Position, Tile> tiles) {
        if (tiles.isEmpty()) {
            return false; 
        }

        boolean sameRow = true;
        boolean sameColumn = true;

        Iterator<Position> iterator = tiles.keySet().iterator();
        Position firstPosition = iterator.next();

        int initialRow = firstPosition.row();
        int initialColumn = firstPosition.column();

        while (iterator.hasNext()) {
            Position position = iterator.next();
            if (position.row() != initialRow) {
                sameRow = false;
            }
            if (position.column() != initialColumn) {
                sameColumn = false;
            }
        }

        return sameRow || sameColumn;
    }

    private boolean isWordConnected(Map<Position, Tile> tiles) {
        for (Position position : tiles.keySet()) {
            if (Boolean.TRUE.equals(gameBoard.isNotEmpty(position))) {
                return true; 
            }

            int row = position.row();
            int col = position.column();

            if ((row > 1 && gameBoard.isNotEmpty(new Position(row - 1, col))) ||
                (row < BoardSizeConstants.BOARD_SIZE && gameBoard.isNotEmpty(new Position(row + 1, col))) ||
                (col > 1 && gameBoard.isNotEmpty(new Position(row, col - 1))) ||
                (col < BoardSizeConstants.BOARD_SIZE && gameBoard.isNotEmpty(new Position(row, col + 1)))) {
                return true;
            }
        }

        return false;
    }

    
    public Set<Word> findAllWords(Map<Position, Tile> tiles) {
        Set<Word> allWords = new HashSet<>();
        
        for (Position position : tiles.keySet()) {

            Word horizontalWord = findWord(position, true);
            if (horizontalWord.getTiles().size() > 1) {
                allWords.add(horizontalWord);
            }

            Word verticalWord = findWord(position, false);
            if (verticalWord.getTiles().size() > 1) {
                allWords.add(verticalWord);
            }
        }

        return allWords;
    }

    private Word findWord(Position start, boolean horizontal) {
        Map<Position, Tile> wordTiles = new HashMap<>();

        Position position = start;
        while (true) {
            Tile tile = getTileAtPosition(position);
            if (tile == null) break;
            wordTiles.put(position, tile);
            position = horizontal ? new Position(position.row(), position.column() - 1) : new Position(position.row() - 1, position.column());
        }

        position = horizontal ? new Position(start.row(), start.column() + 1) : new Position(start.row() + 1, start.column());
        while (true) {
            Tile tile = getTileAtPosition(position);
            if (tile == null) break;
            wordTiles.put(position, tile);
            position = horizontal ? new Position(position.row(), position.column() + 1) : new Position(position.row() + 1, position.column());
        }

        return new Word(wordTiles);
    }

    private Tile getTileAtPosition(Position position) {
        if (position.row() < 1 || position.row() > BoardSizeConstants.BOARD_SIZE ||
            position.column() < 1 || position.column() > BoardSizeConstants.BOARD_SIZE) {
            return null;
        }

        return gameBoard.getSquares()[position.row() - 1][position.column() - 1].getTile();
    }

  
    public int calculateMoveScore(Map<Position, Tile> tiles) {
        int totalScore = 0;
        
        Set<Word> words = findAllWords(tiles);
     

        for (Word word : words) {
        	
            totalScore += calculateWordScore(word);
        }

        return totalScore;
    }
    
    
    

}
