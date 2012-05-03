package de.hszg.tinyblog.view;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;

public class ArticleOverviewBeanTest {

	@Test
	public void testGetArticleListNoArticleInDatabase() {
		ArticleOverviewBean aob = new ArticleOverviewBean();
		List<Article> articles = aob.getArticleList();
		assertEquals(0, articles.size());
	}

}
