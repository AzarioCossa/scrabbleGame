package scrabble.model;

public class Square {

    protected Tile tile;
    protected SquareType squareType;
    

    protected Square(SquareType squareType) {
        this.tile = null;
        this.squareType = squareType;
 
    }
	public SquareType getSquareType() {
		return this.squareType;
	}
    public Tile getTile() {
        return tile;
    }

    public void placeTileSquare(Tile tile) {
        this.tile = tile;
    }

    public Boolean isEmpty() {
        return this.tile == null;
    }
}
