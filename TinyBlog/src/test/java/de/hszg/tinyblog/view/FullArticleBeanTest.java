package de.hszg.tinyblog.view;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.User;

public class FullArticleBeanTest {

	private FullArticleBean fab;
	private User user = new User("Bob", "secret", "email@example.org");
	private Article article = new Article("Title", "Content", user);

	@Before
	public void setUp() {
		fab = new FullArticleBean();
	}

	@Test
	public void testGetArticle() {
		fab.setArticle(article);
		assertEquals(article.getTitle(), fab.getArticle().getTitle());
		assertEquals(article.getContent(), fab.getArticle().getContent());
	}

}
