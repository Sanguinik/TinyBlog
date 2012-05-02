/**
 * 
 */
package de.hszg.tinyblog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.EmfFactory;
import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.User;

/**
 * @author marlene
 * 
 */
public class ArticleServiceTest {

	private static final String MY_CONTENT = "My content.";
	private static final String MY_TITLE = "MyTitle";
	private static final String NEW_CONTENT = "Fresh Content";
	private static final String NEW_TITLE = "New Title";
	private static long articleId;
	private static final long FALSE_ID = articleId + 1;
	private ArticleService articleService;
	private User user = new User("Marlene", "secret", "marlene@example.org");
	private Article article = new Article(MY_TITLE, MY_CONTENT, user);
	private EntityManagerFactory emf;

	@Before
	public void setUp() throws Exception {

		emf = EmfFactory.getInstance();
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.persist(article);
		entityManager.getTransaction().commit();
		entityManager.close();
		articleService = new ArticleServiceImpl();
		articleId = article.getId();
	}

	@After
	public void tearDown() throws Exception {
		emf.close();
		EmfFactory.reset();
	}

	@Test
	public void testShowAllArticles() {
		List<Article> articleList = articleService.findAllArticles();
		assertEquals(1, articleList.size());

	}

	@Test
	public void testAddArticle() {
		Article article2 = new Article(NEW_TITLE, NEW_CONTENT, user);
		assertTrue(articleService.addArticle(article2));
		List<Article> articleList = articleService.findAllArticles();
		assertEquals(2, articleList.size());
	}

	@Test
	public void testAddArticleFailParametersMustNotBeNull() {
		assertFalse(articleService.addArticle(null));
	}

	@Test
	public void testRemoveArticle() {
		List<Article> articleList = articleService.findAllArticles();
		assertEquals(1, articleList.size());

		assertTrue(articleService.removeArticle(article));
		articleList = articleService.findAllArticles();
		assertEquals(0, articleList.size());
	}

	@Test
	public void testRemoveArticleFailParameterMustNotBeNull() {
		assertFalse(articleService.removeArticle(null));
	}

	@Test
	public void testEditArticle() {
		article.setTitle(NEW_TITLE);
		article.setContent(NEW_CONTENT);

		assertTrue(articleService.editArticle(article));

		article = articleService.findArticleById(articleId);

		assertEquals(NEW_TITLE, article.getTitle());
		assertEquals(NEW_CONTENT, article.getContent());

	}

	@Test
	public void testEditArticleFailParameterMustNotBeNull() {
		assertFalse(articleService.editArticle(null));
	}

	@Test
	public void testFindArticleById() {
		Article foundArticle = articleService.findArticleById(articleId);
		assertEquals(MY_TITLE, foundArticle.getTitle());
		assertEquals(MY_CONTENT, foundArticle.getContent());

	}

	@Test
	public void testFindArticleByIdFailNoArticleWithGivenId() {
		assertNull(articleService.findArticleById(FALSE_ID));
	}

}
