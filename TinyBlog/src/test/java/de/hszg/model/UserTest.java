package de.hszg.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	User user = new User();
	
	
	
	@Test
	public void nameTest() {
		user.setName("John Doe");
		assertEquals("John Doe",user.getName());
	}
	
	@Test
	public void emailTest() {
		user.setEmail("email@example.org");
		assertEquals("email@example.org",user.getEmail());
		
	}
	
	@Test
	public void passwordTest() {
		user.setPassword("12345");
		assertEquals("12345",user.getPassword());
	}
	

}
