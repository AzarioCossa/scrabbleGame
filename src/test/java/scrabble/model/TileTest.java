package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {
	
	@Test
    public void testConstructorAndGetLetter() {
        // Créer une tuile avec une lettre spécifique
        Letters letter = Letters.A;
        Tile tile = new Tile(letter);

        // Vérifier que la lettre de la tuile correspond à la lettre spécifiée
        assertEquals(letter, tile.getLetter());
    }

}
