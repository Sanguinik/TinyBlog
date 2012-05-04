package de.hszg.tinyblog.persistence.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class ArticleTest {

	User user = new User("John Doe", "email@example.org", "12345");

	Article article = new Article("Neuer Eintrag", "Ich bin der Inhalt", user);
	Date date = article.getPublishingDate();

	@Test
	public void testGetTitle() {
		assertEquals("Neuer Eintrag", article.getTitle());
	}

	@Test
	public void testGetContent() {
		assertEquals("Ich bin der Inhalt", article.getContent());
	}

	@Test
	public void testGetPublishingDate() {
		assertEquals(date, article.getPublishingDate());
	}
}
