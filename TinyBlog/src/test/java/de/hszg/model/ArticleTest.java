package de.hszg.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArticleTest {
	
	Article article = new Article();

	@Test
	public void titleTest() {
		article.setTitle("Neuer Eintrag");
		assertEquals("Neuer Eintrag", article.getTitle());
	}

	@Test
	public void contentTest() {
		article.setContent("Ich bin der Inhalt");
		assertEquals("Ich bin der Inhalt",article.getContent());
	}
	
}
