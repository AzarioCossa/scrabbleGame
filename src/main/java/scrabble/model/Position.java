package scrabble.model;

import java.util.Objects;

public class Position {

	private Integer row;
	private Integer column;

	
	public Position(Integer row, Integer column ) {
		this.row = row;
		this.column = column;

	}

	@Override
	public int hashCode() {
		return Objects.hash(column, row);
	}

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

	public int column() {
		return column;
	}

	public int row() {
		return row;
	}




}
