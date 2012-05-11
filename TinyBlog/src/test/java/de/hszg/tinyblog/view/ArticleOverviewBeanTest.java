package de.hszg.tinyblog.view;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.User;
import de.hszg.tinyblog.util.StringCreatorTestHelper;

public class ArticleOverviewBeanTest {

	private ArticleOverviewBean aob;
	private StringCreatorTestHelper scth = new StringCreatorTestHelper();

	@Before
	public void setUp() {
		aob = new ArticleOverviewBean();
	}

	@Test
	public void testGetArticleListNoArticleInDatabase() {
		List<Article> articles = aob.getArticleList();
		assertEquals(0, articles.size());
	}

	@Test
	public void testArticlePreview() {
		String largeString = scth.longStringGenerator();
		User user = new User("Joe", "secret", "email");
		Article article = new Article("Title", largeString, user);
		String preview = aob.articlePreview(article);
		String expectedString = largeString.substring(0, 199) + " [...]";

		assertEquals(expectedString, preview);

		String shortString = "This is a short string";
		article = new Article("Title2", shortString, user);
		preview = aob.articlePreview(article);

		assertEquals(shortString, preview);
	}
}
