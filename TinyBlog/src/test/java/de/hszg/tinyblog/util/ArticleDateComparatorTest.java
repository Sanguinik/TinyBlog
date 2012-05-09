package de.hszg.tinyblog.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.User;

public class ArticleDateComparatorTest {

	@Test
	public void test() {

		List<Article> articles = new ArrayList<Article>();
		Comparator<Article> comp = new ArticleDateComparator();
		Comparator<Article> reverse = Collections.reverseOrder(comp);

		User user = new User("Homer", "donut", "homer@simpson.com");

		Article article1 = new Article("Title", "Content", user);
		sleepForASecond();

		Article article2 = new Article("Title2", "Content2", user);
		sleepForASecond();

		Article article3 = new Article("Title3", "Content3", user);

		articles.add(article1);
		articles.add(article2);
		articles.add(article3);

		Collections.sort(articles, reverse);

		assertEquals(article3, articles.get(0));
		assertEquals(article2, articles.get(1));
		assertEquals(article1, articles.get(2));

	}

	private void sleepForASecond() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
