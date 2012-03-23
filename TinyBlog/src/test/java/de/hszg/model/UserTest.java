package de.hszg.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	User user = new User("John Doe", "12345", "email@example.org");
	
	
	
	@Test
	public void getNameTest() {
		assertEquals("John Doe",user.getName());
	}
	
	@Test
	public void getEmailTest() {
		assertEquals("email@example.org",user.getEmail());
		
	}
	
	@Test
	public void getPasswordTest() {
		assertEquals("12345",user.getPassword());
	}
	

}
