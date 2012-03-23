package de.hszg.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArticleTest {
	
	User user = new User("John Doe","email@example.org","12345");
	
	Article article = new Article("Neuer Eintrag", "Ich bin der Inhalt", user);

	@Test
	public void titleTest() {
		assertEquals("Neuer Eintrag", article.getTitle());
	}

	@Test
	public void contentTest() {
		assertEquals("Ich bin der Inhalt",article.getContent());
	}
	
	
	
}
