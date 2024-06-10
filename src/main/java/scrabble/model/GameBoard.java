package scrabble.model;

import scrabble.model.utils.TilePlacementException;

/**
 * The GameBoard class represents the Scrabble game board.
 * It initializes the board with squares having different types and provides methods to interact with the board.
 */
public class GameBoard {

    /**
     * The 2D array of squares representing the Scrabble game board.
     */
    private Square[][] squares;

    /**
     * Constructs a new GameBoard and initializes the squares based on the board layout.
     */
    public GameBoard() {
        squares = new Square[BoardSizeConstants.BOARD_SIZE][BoardSizeConstants.BOARD_SIZE];

        for (int row = 0; row < BoardSizeConstants.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardSizeConstants.BOARD_SIZE; column++) {
                String bonusType = BoardSizeConstants.boardLayout[row][column];
                switch (bonusType) {
                    case "C":
                        squares[row][column] = new Square(SquareType.CENTRAL);
                        break;
                    case "TW":
                        squares[row][column] = new Square(SquareType.TRIPLE_WORD);
                        break;
                    case "DW":
                        squares[row][column] = new Square(SquareType.DOUBLE_WORD);
                        break;
                    case "TL":
                        squares[row][column] = new Square(SquareType.TRIPLE_LETTER);
                        break;
                    case "DL":
                        squares[row][column] = new Square(SquareType.DOUBLE_LETTER);
                        break;
                    default:
                        squares[row][column] = new Square(SquareType.STANDARD);
                        break;
                }
            }
        }
    }

    /**
     * Returns the 2D array of squares representing the game board.
     * 
     * @return the 2D array of squares
     */
    public Square[][] getSquares() {
        return squares;
    }

    /**
     * Checks if the entire game board is empty.
     * 
     * @return true if the board is empty, false otherwise
     */
    public Boolean isEmpty() {
        for (int row = 0; row < BoardSizeConstants.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardSizeConstants.BOARD_SIZE; column++) {
                if (squares[row][column].getTile() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a specific position on the game board is empty.
     * 
     * @param position the position to check
     * @return true if the position is empty, false otherwise
     */
    public Boolean isEmpty(Position position) {
        int row = position.row() - 1;
        int col = position.column() - 1;
        return squares[row][col].getTile() == null;
    }

    /**
     * Checks if a specific position on the game board is not empty.
     * 
     * @param position the position to check
     * @return true if the position is not empty, false otherwise
     */
    public Boolean isNotEmpty(Position position) {
        int row = position.row() - 1;
        int col = position.column() - 1;
        return squares[row][col].getTile() != null;
    }

    /**
     * Places a tile on the game board at the specified row and column.
     * Throws TilePlacementException if the row or column index is out of bounds.
     * 
     * @param tile the tile to place
     * @param row the row index (0-based)
     * @param col the column index (0-based)
     * @throws TilePlacementException if the row or column index is out of bounds
     */
    public void placeTileGameBoard(Tile tile, int row, int col) throws TilePlacementException {
        if (row < 0 || row >= BoardSizeConstants.BOARD_SIZE || col < 0 || col >= BoardSizeConstants.BOARD_SIZE) {
            throw new TilePlacementException("Row or column index is out of bounds.");
        }
        squares[row][col].placeTileSquare(tile);
    }
}
