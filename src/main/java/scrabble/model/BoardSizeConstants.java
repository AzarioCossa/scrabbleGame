package scrabble.model;

/**
 * The BoardSizeConstants class provides constants related to the Scrabble board size and layout.
 * It contains the board dimensions and the initial layout of the board with special tile positions.
 * This class is not meant to be instantiated.
 */
public class BoardSizeConstants {

    /**
     * Private constructor to prevent instantiation.
     */
    private BoardSizeConstants() {}

    /**
     * The size of the Scrabble board (15x15).
     */
    public static final int BOARD_SIZE = 15;

    /**
     * The middle index of the Scrabble board (used to determine the central position).
     */
    public static final int MIDDLE_INDEX = 8;

    /**
     * The initial layout of the Scrabble board, represented as a 2D array of strings.
     * The layout indicates the positions of special tiles:
     * "TW" - Triple Word Score
     * "DW" - Double Word Score
     * "TL" - Triple Letter Score
     * "DL" - Double Letter Score
     * "C"  - Center position (usually the starting point)
     */
    protected static final String[][] boardLayout = {
            {"TW", "", "", "DL", "", "", "", "TW", "", "", "", "DL", "", "", "TW"},
            {"", "DW", "", "", "", "TL", "", "", "", "TL", "", "", "", "DW", ""},
            {"", "", "DW", "", "", "", "DL", "", "DL", "", "", "", "DW", "", ""},
            {"DL", "", "", "DW", "", "", "", "DL", "", "", "", "DW", "", "", "DL"},
            {"", "", "", "", "DW", "", "", "", "", "", "DW", "", "", "", ""},
            {"", "TL", "", "", "", "TL", "", "", "", "TL", "", "", "", "TL", ""},
            {"", "", "DL", "", "", "", "DL", "", "DL", "", "", "", "DL", "", ""},
            {"TW", "", "", "DL", "", "", "", "C", "", "", "", "DL", "", "", "TW"},
            {"", "", "DL", "", "", "", "DL", "", "DL", "", "", "", "DL", "", ""},
            {"", "TL", "", "", "", "TL", "", "", "", "TL", "", "", "", "TL", ""},
            {"", "", "", "", "DW", "", "", "", "", "", "DW", "", "", "", ""},
            {"DL", "", "", "DW", "", "", "", "DL", "", "", "", "DW", "", "", "DL"},
            {"", "", "DW", "", "", "", "DL", "", "DL", "", "", "", "DW", "", ""},
            {"", "DW", "", "", "", "TL", "", "", "", "TL", "", "", "", "DW", ""},
            {"TW", "", "", "DL", "", "", "", "TW", "", "", "", "DL", "", "", "TW"}
        };
}
