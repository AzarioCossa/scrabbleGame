package scrabble.model;

public enum Letters {
	
	A(9),
	B(2),
	C(2),
	D(3),
	E(15),
	F(2),
	G(2),
	H(2),
	I(8),
	J(1),
	K(1),
	L(5),
	M(3),
	N(6),
	O(6),
	P(2),
	Q(1),
	R(6),
	S(6),
	T(6),
	U(6),
	V(2),
	W(1),
	X(1),
	Y(1),
	Z(1),
	JOCKER(2);
	
	 Letters(int quantity) {
		this.quantity = quantity;
	}

	private int quantity;

	public int getQuantity() {
		return this.quantity;
	}	
}
