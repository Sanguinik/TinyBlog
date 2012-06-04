package de.hszg.tinyblog.view;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.User;

public class EditArticleBeanTest {

	private EditArticleBean eab;

	private static final String TITLE = "Title";
	private static final String CONTENT = "Content";
	private User user = new User("Bob", "secret", "email@example.org");

	private Article article = new Article(TITLE, CONTENT, user);

	@Before
	public void setUp() {
		eab = new EditArticleBean();
	}

	@Test
	public void testSetAndGetTitle() {
		eab.setTitle(TITLE);
		assertEquals(TITLE, eab.getTitle());
	}

	@Test
	public void testSetAndGetContent() {
		eab.setContent(CONTENT);
		assertEquals(CONTENT, eab.getContent());
	}

	@Test
	public void testSetGetArticle() {
		eab.setArticle(article);
		assertEquals(article.getTitle(), eab.getArticle().getTitle());
		assertEquals(article.getContent(), eab.getArticle().getContent());
	}

}
