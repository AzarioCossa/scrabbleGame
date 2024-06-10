package scrabble.model;

/**
 * The FrenchLetters enum represents the letters used in the French version of Scrabble,
 * along with their respective quantities and weights.
 */
public enum FrenchLetters { 
	
    /**
     * Letter A: Quantity - 9, Weight - 1
     */
    A(9, 1),    
    /**
     * Letter B: Quantity - 2, Weight - 3
     */
    B(2, 3),    
    /**
     * Letter C: Quantity - 2, Weight - 3
     */
    C(2, 3),    
    /**
     * Letter D: Quantity - 3, Weight - 2
     */
    D(3, 2),    
    /**
     * Letter E: Quantity - 15, Weight - 1
     */
    E(15, 1),   
    /**
     * Letter F: Quantity - 2, Weight - 4
     */
    F(2, 4),    
    /**
     * Letter G: Quantity - 2, Weight - 2
     */
    G(2, 2),    
    /**
     * Letter H: Quantity - 2, Weight - 4
     */
    H(2, 4),    
    /**
     * Letter I: Quantity - 8, Weight - 1
     */
    I(8, 1),    
    /**
     * Letter J: Quantity - 1, Weight - 8
     */
    J(1, 8),    
    /**
     * Letter K: Quantity - 1, Weight - 10
     */
    K(1, 5),    
    /**
     * Letter L: Quantity - 5, Weight - 1
     */
    L(5, 1),    
    /**
     * Letter M: Quantity - 3, Weight - 2
     */
    M(3, 3),    
    /**
     * Letter N: Quantity - 6, Weight - 1
     */
    N(6, 1),    
    /**
     * Letter O: Quantity - 6, Weight - 1
     */
    O(6, 1),    
    /**
     * Letter P: Quantity - 2, Weight - 3
     */
    P(2, 3),    
    /**
     * Letter Q: Quantity - 1, Weight - 8
     */
    Q(1, 10),   
    /**
     * Letter R: Quantity - 6, Weight - 1
     */
    R(6, 1),    
    /**
     * Letter S: Quantity - 6, Weight - 1
     */
    S(6, 1),    
    /**
     * Letter T: Quantity - 6, Weight - 1
     */
    T(6, 1),    
    /**
     * Letter U: Quantity - 6, Weight - 1
     */
    U(6, 1),    
    /**
     * Letter V: Quantity - 2, Weight - 4
     */
    V(2, 4),    
    /**
     * Letter W: Quantity - 1, Weight - 10
     */
    W(1, 4),    
    /**
     * Letter X: Quantity - 1, Weight - 10
     */
    X(1, 8),    
    /**
     * Letter Y: Quantity - 1, Weight - 10
     */
    Y(1, 4),    
    /**
     * Letter Z: Quantity - 1, Weight - 10
     */
    Z(1, 10),   
    /**
     * Special Joker tile: Quantity - 2, Weight - 0
     */
    JOCKER(2, 0); 

    /**
     * The quantity of the letter tiles.
     */
    private final int quantity; 
    /**
     * The weight (point value) of the letter tiles.
     */
    private final int weight;   

    /**
     * Constructs a FrenchLetters enum with the specified quantity and weight.
     * 
     * @param quantity the number of tiles of this letter
     * @param weight the point value of this letter
     */
    FrenchLetters(int quantity, int weight) {
        this.quantity = quantity;
        this.weight = weight;
    }

    /**
     * Returns the quantity of this letter.
     * 
     * @return the quantity of this letter
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns the weight (point value) of this letter.
     * 
     * @return the weight of this letter
     */
    public int getWeight() {
        return this.weight;
    }    

    /**
     * Returns the FrenchLetters enum corresponding to the specified character.
     * 
     * @param character the character to look up
     * @return the corresponding FrenchLetters enum
     * @throws IllegalArgumentException if the character is not a valid letter
     */
    public static FrenchLetters getLetter(char character) {
        for (FrenchLetters letter : FrenchLetters.values()) {
            if (letter.toString().charAt(0) == Character.toUpperCase(character)) {
                return letter;
            }
        }
        throw new IllegalArgumentException("Invalid character: " + character);
    }

    /**
     * Returns the string representation of this enum constant.
     * 
     * @return the string representation of this enum constant
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
