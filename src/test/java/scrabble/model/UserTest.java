package scrabble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	public void testConstructorAndGetters() {
		String name = "Louis";
		Rack rack = new Rack();
		User user = new User(name,rack);
		assertEquals(name, user.getName());
	}

}
