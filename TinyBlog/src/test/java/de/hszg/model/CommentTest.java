package de.hszg.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommentTest {
	
	User user = new User("John Doe","email@example.org","12345");

	
	Comment comment = new Comment(user, "Toller Artikel");

	@Test
	public void contentTest() {
		assertEquals("Toller Artikel",comment.getContent());
	}

}
