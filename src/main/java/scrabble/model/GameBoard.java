package scrabble.model;


public class GameBoard {

    private Square[][] squares;

    public GameBoard() {

        squares = new Square[ BoardSizeConstants.DEFAULT_NUM_ROWS][BoardSizeConstants.DEFAULT_NUM_COLUMNS];

        for (int row = 0; row < BoardSizeConstants.DEFAULT_NUM_ROWS; row++) {
            for (int column = 0; column < BoardSizeConstants.DEFAULT_NUM_COLUMNS; column++) {
                squares[row][column] = new Square();
            }
        }
        squares[7][7] = new SquareStar();
    }

    public Square[][] getSquares() {
        return squares;
    }
    public Boolean isEmpty() {
    	for (int row = 0; row < BoardSizeConstants.DEFAULT_NUM_ROWS; row++) {
            for (int column = 0; column < BoardSizeConstants.DEFAULT_NUM_COLUMNS; column++) {
            	if (squares[row][column].getTile() != null) {
                	return false;
                }
            }
        }
    	return true;
    }
    
    
    
    public Boolean isEmpty(Position position) {
    	int row = position.row() - 1;
        int col = position.column() - 1;
        boolean isEmpty;
        
        if (squares[row][col].getTile() == null) {
        	isEmpty = true;
        }
        else {
        	isEmpty = false;
        }
        return isEmpty;
    }
    
    public Boolean isNotEmpty(Position position) {
    	int row = position.row() - 1;
        int col = position.column() - 1;
        boolean isNotEmpty;
        
        if (squares[row][col].getTile() == null) {
        	isNotEmpty = false;
        }
        else {
        	isNotEmpty = true;
        }
        return isNotEmpty;
    }
    
    public void placeTileGameBoard(Tile tile, int row, int col) {
        squares[row][col].placeTileSquare(tile);
    }
}