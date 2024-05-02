package scrabble.model;



public class GameBoard {
	
    private Square[][] squares;
	private int sizeX;
	private int sizeY;

	
    public GameBoard(int x, int y) {
    	this.sizeX = x;
    	this.sizeY = y;
        squares = new Square[x][y];  
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                squares[i][j] = new Square(i+1, j+1);  
            }
        }
        squares[7][7] = new SquareStar(); 
    }

    
	public Square[][] getSquares() {
		return squares;
	}
	
	
	public int getSizeX() {
		return sizeX;
	}

	
	public int getSizeY() {
		return sizeY;
	}
}
