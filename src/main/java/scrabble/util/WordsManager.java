package scrabble.util;

import scrabble.model.*;

import java.util.Map;

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

            wordScore += tile.getWeight() * letterMultiplier;
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
        // Implement validation logic here
        return true;
    }
}
