package de.hszg.tinyblog.persistence.model;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Comment;
import de.hszg.tinyblog.persistence.model.User;

public class CommentTest {
	
	User user = new User("John Doe","email@example.org","12345");

	
	Comment comment = new Comment(user, "Toller Artikel");

	@Test
	public void contentTest() {
		assertEquals("Toller Artikel",comment.getContent());
	}

}
