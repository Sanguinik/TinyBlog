package de.hszg.tinyblog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;
import de.hszg.tinyblog.persistence.model.User;
import de.hszg.tinyblog.util.EmfFactory;

/**
 * This integration test was created for testing the removal of a comment from
 * an article. There was a bug where the comments were not removed correctly.
 * 
 * @author marlene
 * 
 */

public class ArticleAndCommentIntegrationTest {

	CommentService commentService = new CommentServiceImpl();
	ArticleService articleService = new ArticleServiceImpl();
	private User user = new User("John Doe", "secret", "john@example.org");
	private Article article = new Article("My title", "My content", user);
	private EntityManagerFactory emf;
	private long articleId;

	@Before
	public void setUp() {

		emf = EmfFactory.getInstance();
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.persist(article);
		entityManager.getTransaction().commit();
		entityManager.close();
		articleId = article.getId();

	}

	@After
	public void tearDown() {
		emf.close();
		EmfFactory.reset();
	}

	@Test
	public void testCommentCreationAndRemoval() {
		article = articleService.findArticleById(articleId);
		assertEquals(0, article.getNumberOfComments());

		Comment commentByUser = new Comment(user, "This is my comment");
		assertTrue(commentService.addComment(commentByUser, article));

		article = articleService.findArticleById(articleId);
		assertEquals(1, article.getNumberOfComments());

		assertTrue(commentService.removeComment(commentByUser, article));
		article = articleService.findArticleById(articleId);
		assertEquals(0, article.getNumberOfComments());

	}

}
