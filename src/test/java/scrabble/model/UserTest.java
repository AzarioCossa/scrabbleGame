package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	public void testConstructorAndGetters() {
		Bag bag = new Bag();

		String name = "John";
		User user = new User(bag, name);

		assertEquals(name, user.getName());
	}

}
