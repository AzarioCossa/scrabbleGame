package scrabble.model;

public enum FrenchLetters { 
	
	A(9,1),    // Letter A: Quantity - 9, Weight - 1
	B(2,3),    // Letter B: Quantity - 2, Weight - 3
	C(2,3),    // Letter C: Quantity - 2, Weight - 3
	D(3,2),    // Letter D: Quantity - 3, Weight - 2
	E(15,1),   // Letter E: Quantity - 15, Weight - 1
	F(2,4),    // Letter F: Quantity - 2, Weight - 4
	G(2,2),    // Letter G: Quantity - 2, Weight - 2
	H(2,4),    // Letter H: Quantity - 2, Weight - 4
	I(8,1),    // Letter I: Quantity - 8, Weight - 1
	J(1,8),    // Letter J: Quantity - 1, Weight - 8
	K(1,5),   // Letter K: Quantity - 1, Weight - 10
	L(5,1),    // Letter L: Quantity - 5, Weight - 1
	M(3,3),    // Letter M: Quantity - 3, Weight - 2
	N(6,1),    // Letter N: Quantity - 6, Weight - 1
	O(6,1),    // Letter O: Quantity - 6, Weight - 1
	P(2,3),    // Letter P: Quantity - 2, Weight - 3
	Q(1,10),    // Letter Q: Quantity - 1, Weight - 8
	R(6,1),    // Letter R: Quantity - 6, Weight - 1
	S(6,1),    // Letter S: Quantity - 6, Weight - 1
	T(6,1),    // Letter T: Quantity - 6, Weight - 1
	U(6,1),    // Letter U: Quantity - 6, Weight - 1
	V(2,4),    // Letter V: Quantity - 2, Weight - 4
	W(1,4),   // Letter W: Quantity - 1, Weight - 10
	X(1,8),   // Letter X: Quantity - 1, Weight - 10
	Y(1,4),   // Letter Y: Quantity - 1, Weight - 10
	Z(1,10),   // Letter Z: Quantity - 1, Weight - 10
	JOCKER(2,0); // Special Joker tile: Quantity - 2, Weight - 0
	
	FrenchLetters(int quantity, int weight) {
		this.quantity = quantity;
		this.weight = weight;
	}


	private final int quantity; 
	private final int weight;   

	public int getQuantity() {
		return this.quantity;
	}

	public int getWeight() {
		return this.weight;
	}	
	
	public static FrenchLetters getLetter(char character) {
	    for (FrenchLetters letter : FrenchLetters.values()) {
	        if (letter.toString().charAt(0) == Character.toUpperCase(character)) {
	            return letter;
	        }
	    }
	    throw new IllegalArgumentException("Invalid character: " + character);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}