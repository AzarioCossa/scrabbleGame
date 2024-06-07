package scrabble.model;


public class GameBoard {

    private Square[][] squares;

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

    public Square[][] getSquares() {
        return squares;
    }
    public Boolean isEmpty() {
    	for (int row = 0; row < BoardSizeConstants.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardSizeConstants.BOARD_SIZE ; column++) {
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