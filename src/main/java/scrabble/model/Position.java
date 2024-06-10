package scrabble.model;

import java.util.Objects;

/**
 * The Position class represents a position on the Scrabble board,
 * defined by its row and column indices.
 */
public class Position {

    /**
     * The row index of the position.
     */
    private Integer row;

    /**
     * The column index of the position.
     */
    private Integer column;

    /**
     * Constructs a new Position with the specified row and column indices.
     * 
     * @param row the row index of the position
     * @param column the column index of the position
     */
    public Position(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the hash code value for this position.
     * The hash code is based on the row and column indices.
     * 
     * @return the hash code value for this position
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    /**
     * Compares this position to the specified object.
     * The result is true if and only if the argument is not null and is a Position object that has the same row and column indices as this object.
     * 
     * @param obj the object to compare this Position against
     * @return true if the given object represents a Position equivalent to this position, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        return Objects.equals(column, other.column) && Objects.equals(row, other.row);
    }

    /**
     * Returns the column index of this position.
     * 
     * @return the column index
     */
    public int column() {
        return column;
    }

    /**
     * Returns the row index of this position.
     * 
     * @return the row index
     */
    public int row() {
        return row;
    }
}
