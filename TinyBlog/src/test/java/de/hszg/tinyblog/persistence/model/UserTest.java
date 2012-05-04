package de.hszg.tinyblog.persistence.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {

	User user = new User("John Doe", "12345", "email@example.org");

	@Test
	public void testGetName() {
		assertEquals("John Doe", user.getName());
	}

	@Test
	public void testGetEmail() {
		assertEquals("email@example.org", user.getEmail());

	}

	@Test
	public void testGetPassword() {
		assertEquals("12345", user.getPassword());
	}

}
