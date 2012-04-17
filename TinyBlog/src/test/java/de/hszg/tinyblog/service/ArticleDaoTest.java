package de.hszg.tinyblog.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.model.Article;
import de.hszg.tinyblog.model.User;

public class ArticleDaoTest {
	
	private static final String MY_CONTENT = "My content.";
	private static final String MY_TITLE = "My title";
	private static final String MY_NEW_CONTENT = "My new content";
	private static final String MY_NEW_TITLE = "My new title";
	private ArticleDao articleDao;
	private User user = new User("Marlene","secret","marlene@example.org");
	
	@Before
	public void setUp(){
		articleDao = new ArticleDaoMock();
	}

	@Test
	public void testAddAndRemoveArticle() {
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		assertTrue(articleDao.addArticle(article));
		
		List<Article> articles = articleDao.findAllArticles();
		assertTrue(articles.contains(article));
		
		assertTrue(articleDao.removeArticle(article));
		assertFalse(articles.contains(article));
		
	}
	

	@Test
	public void testAddAndEditArticle(){
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		assertTrue(articleDao.addArticle(article));
		assertEquals(MY_TITLE, article.getTitle());
		assertEquals(MY_CONTENT, article.getContent());
		
		article.setTitle(MY_NEW_TITLE);
		article.setContent(MY_NEW_CONTENT);
	
		articleDao.setArticle(article);
		
		assertEquals(MY_NEW_TITLE, article.getTitle());
		assertEquals(MY_NEW_CONTENT, article.getContent());
		
		
	}

	@Test
	public void testFindArticleById(){
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		assertTrue(articleDao.addArticle(article));
		long id = article.getId();
		Article articleById = articleDao.findArticleById(id);
		assertEquals(articleById, article);
		
	}

}
