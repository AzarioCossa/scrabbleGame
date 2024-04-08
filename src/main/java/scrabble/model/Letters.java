package scrabble.model;

public enum Letters {
	
	A(9,1),
	B(2,3),
	C(2,3),
	D(3,2),
	E(15,1),
	F(2,4),
	G(2,2),
	H(2,4),
	I(8,1),
	J(1,8),
	K(1,10),
	L(5,1),
	M(3,2),
	N(6,1),
	O(6,1),
	P(2,3),
	Q(1,8),
	R(6,1),
	S(6,1),
	T(6,1),
	U(6,1),
	V(2,4),
	W(1,10),
	X(1,10),
	Y(1,10),
	Z(1,10),
	JOCKER(2,0);
	
	 Letters(int quantity, int  weight) {
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
}
