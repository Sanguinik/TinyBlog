package de.hszg.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommentTest {
	
	Comment comment = new Comment();

	@Test
	public void contentTest() {
		comment.setContent("Toller Artikel");
		assertEquals("Toller Artikel",comment.getContent());
	}

}
