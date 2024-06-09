package scrabble.util;

import scrabble.model.*;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
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
            System.out.println(bonus);
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

    /**
     * Validate the words formed by the tiles on the board.
     *
     * @param tiles the map of positions and tiles to validate
     * @return true if the words are valid, false otherwise
     */
    public boolean validateWords(Map<Position, Tile> tiles) {
        if (gameBoard.isEmpty()) {


            if (tiles.size() < 2) {
                return false; // Le mot doit être au moins de 2 lettres
            }

            boolean passesThroughCenter = false;
            Position centerPosition = new Position(BoardSizeConstants.BOARD_SIZE / 2 + 1, BoardSizeConstants.BOARD_SIZE / 2 + 1);

            for (Position position : tiles.keySet()) {
                if (position.equals(centerPosition)) {
                    passesThroughCenter = true;
                    break;
                }
            }

            if (!passesThroughCenter) {
                return false; // Le mot doit passer par le centre
            }

            if (!isWordAligned(tiles)) {
                return false; // Le mot doit être aligné horizontalement ou verticalement
            }

        } else {
            if (!isWordAligned(tiles)) {
                return false; // Le mot doit être aligné horizontalement ou verticalement
            }

            if (!isWordConnected(tiles)) {
                return false; // Le mot doit être connecté à un mot existant
            }
        }

        return true;
    }

    private boolean isWordAligned(Map<Position, Tile> tiles) {
        if (tiles.isEmpty()) {
            return false; // S'il n'y a pas de tuiles, le mot n'est pas aligné
        }

        boolean sameRow = true;
        boolean sameColumn = true;

        Iterator<Position> iterator = tiles.keySet().iterator();
        Position firstPosition = iterator.next(); // Obtenez la première position s'il y en a une

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
            if (gameBoard.isNotEmpty(position)) {
                return true; // Le mot est connecté à un mot existant
            }

            // Vérifiez les tuiles adjacentes
            int row = position.row();
            int col = position.column();

            if ((row > 1 && gameBoard.isNotEmpty(new Position(row - 1, col))) ||
                (row < BoardSizeConstants.BOARD_SIZE && gameBoard.isNotEmpty(new Position(row + 1, col))) ||
                (col > 1 && gameBoard.isNotEmpty(new Position(row, col - 1))) ||
                (col < BoardSizeConstants.BOARD_SIZE && gameBoard.isNotEmpty(new Position(row, col + 1)))) {
                return true;
            }
        }

        return false; // Le mot n'est pas connecté à un mot existant
    }

    
    public Set<Word> findAllWords(Map<Position, Tile> tiles) {
        Set<Word> allWords = new HashSet<>();

        // Iterate over each position in the tiles map
        for (Position position : tiles.keySet()) {
            // Check horizontally
            Word horizontalWord = findWord(position, true);
            if (horizontalWord.getTiles().size() > 1) { // Word must be more than one tile
                allWords.add(horizontalWord);
            }

            // Check vertically
            Word verticalWord = findWord(position, false);
            if (verticalWord.getTiles().size() > 1) { // Word must be more than one tile
                allWords.add(verticalWord);
            }
        }

        return allWords;
    }

    private Word findWord(Position start, boolean horizontal) {
        Map<Position, Tile> wordTiles = new HashMap<>();

        // Find the start of the word
        Position position = start;
        while (true) {
            Tile tile = getTileAtPosition(position);
            if (tile == null) break;
            wordTiles.put(position, tile);
            position = horizontal ? new Position(position.row(), position.column() - 1) : new Position(position.row() - 1, position.column());
        }

        // Find the end of the word
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
        	System.out.println("Found word: " + word);
        	System.out.println(calculateWordScore(word));
            totalScore += calculateWordScore(word);
        }

        return totalScore;
    }
    
    
    

}
