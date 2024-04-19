package scrabble.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	public void testConstructorAndGetters() {
		Rack rack = new Rack(new ArrayList<>());

		String name = "John";
		User user = new User(rack, name);

		assertEquals(rack, user.getRack());
		assertEquals(name, user.getName());
	}

}
