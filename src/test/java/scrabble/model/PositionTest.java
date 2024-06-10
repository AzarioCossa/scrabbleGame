package scrabble.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

class PositionTest {

	private Position position;
	
	@BeforeAll
    public void init() {
		position = new Position(1,1);
	}
    @Test
    void testPositionInitialization() {
        assertEquals(1, position.row());
        assertEquals(1, position.column());
    }

}
