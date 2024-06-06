package scrabble.model;

public class Square {

    protected char symbol;
    protected Tile tile;

    protected Square(char symbol) {
        this.tile = null;
        this.symbol = symbol;
    }

    public Square() {
        this('.');
    }

    public char getSymbol() {
        return symbol;
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
