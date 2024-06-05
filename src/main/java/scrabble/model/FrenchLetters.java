package scrabble.model;

// Enum representing the letters in the game of Scrabble
public enum FrenchLetters { 
	
	// Enum constants with quantity and weight values for each letter
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
	K(1,10),   // Letter K: Quantity - 1, Weight - 10
	L(5,1),    // Letter L: Quantity - 5, Weight - 1
	M(3,2),    // Letter M: Quantity - 3, Weight - 2
	N(6,1),    // Letter N: Quantity - 6, Weight - 1
	O(6,1),    // Letter O: Quantity - 6, Weight - 1
	P(2,3),    // Letter P: Quantity - 2, Weight - 3
	Q(1,8),    // Letter Q: Quantity - 1, Weight - 8
	R(6,1),    // Letter R: Quantity - 6, Weight - 1
	S(6,1),    // Letter S: Quantity - 6, Weight - 1
	T(6,1),    // Letter T: Quantity - 6, Weight - 1
	U(6,1),    // Letter U: Quantity - 6, Weight - 1
	V(2,4),    // Letter V: Quantity - 2, Weight - 4
	W(1,10),   // Letter W: Quantity - 1, Weight - 10
	X(1,10),   // Letter X: Quantity - 1, Weight - 10
	Y(1,10),   // Letter Y: Quantity - 1, Weight - 10
	Z(1,10),   // Letter Z: Quantity - 1, Weight - 10
	JOCKER(2,0); // Special Joker tile: Quantity - 2, Weight - 0
	
	// Constructor for Letters enum
	FrenchLetters(int quantity, int weight) {
		this.quantity = quantity; // Initialize quantity
		this.weight = weight;     // Initialize weight
	}

	// Attributes for quantity and weight of each letter
	private final int quantity; // Number of tiles of this letter
	private final int weight;   // Point value of this letter

	// Getter method to retrieve the quantity of a letter
	public int getQuantity() {
		return this.quantity;
	}

	// Getter method to retrieve the weight of a letter
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