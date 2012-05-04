package de.hszg.tinyblog.persistence.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommentTest {

	User user = new User("John Doe", "email@example.org", "12345");

	Comment comment = new Comment(user, "Toller Artikel");

	@Test
	public void testContent() {
		assertEquals("Toller Artikel", comment.getContent());
	}

}
