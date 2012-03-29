package de.hszg.tinyblog.service;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.model.Article;
import de.hszg.tinyblog.model.User;

public class ArticleServiceTest {

	private ArticleService articleService;

	@Before
	public void setUp(){
		articleService = new ArticleServiceMockImpl();
	}
	
	@Test
	public void testAddArticle() {
		
		String title = "Testtitel";
		String content = "Lorem ipsum";
		User user = new User("Name","Password","test@example.org");
		
		Article article = new Article(title,content,user);
		boolean result = articleService.addArticle(article);
		
		assertTrue(result);
		
		List<Article> articles = articleService.findAllArticles();
		
		assertTrue(articles.contains(article));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddArticleFailWithNull(){
		articleService.addArticle(null);
	}
	
	@Test
	public void testAddArticleExistingArticleFail(){
		
		String title = "Testtitel";
		String content = "Lorem ipsum";
		User user = new User("Name","Password","test@example.org");
		
		Article article = new Article(title,content,user);
		
		articleService.addArticle(article);
		assertFalse(articleService.addArticle(article));
	}

}
