package scrabble.model;

/**
 * The SquareType enum represents the types of squares on the Scrabble game board.
 * Each type corresponds to a different bonus or standard square.
 */
public enum SquareType {

    /**
     * The central square where the first word is placed.
     */
    CENTRAL,

    /**
     * Double letter score square.
     */
    DOUBLE_LETTER,

    /**
     * Double word score square.
     */
    DOUBLE_WORD,

    /**
     * Standard square with no bonus.
     */
    STANDARD,

    /**
     * Triple letter score square.
     */
    TRIPLE_LETTER,

    /**
     * Triple word score square.
     */
    TRIPLE_WORD
}
