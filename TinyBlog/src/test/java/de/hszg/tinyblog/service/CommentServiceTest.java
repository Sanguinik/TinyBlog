package de.hszg.tinyblog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;
import de.hszg.tinyblog.persistence.model.User;
import de.hszg.tinyblog.util.EmfFactory;

public class CommentServiceTest {

	private CommentService commentService;
	private User user = new User("John Doe", "secret", "john@example.org");
	private Article article = new Article("My title", "My content", user);
	private Comment commentByUser = new Comment(user, "This is my comment");
	private Comment commentByUnknown = new Comment("Mr. Example",
			"email@example.org", "Here is my comment.");

	private EntityManagerFactory emf;

	@Before
	public void setUp() {
		emf = EmfFactory.getInstance();
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.persist(article);
		entityManager.getTransaction().commit();
		entityManager.close();
		commentService = new CommentServiceImpl();
	}

	@After
	public void tearDown() {
		emf.close();
		EmfFactory.reset();

	}

	@Test
	public void testAddCommentByUser() {

		assertEquals(0, article.getNumberOfComments());

		assertTrue(commentService.addComment(commentByUser, article));

		assertEquals(1, article.getNumberOfComments());

	}

	@Test
	public void testAddCommentByUnknown() {

		assertEquals(0, article.getNumberOfComments());

		assertTrue(commentService.addComment(commentByUnknown, article));

		assertEquals(1, article.getNumberOfComments());
	}

	@Test
	public void testAddCommentFailParametersMustNotBeNull() {

		assertFalse(commentService.addComment(null, article));

		assertFalse(commentService.addComment(commentByUser, null));

		assertFalse(commentService.addComment(null, null));

	}

	@Test
	public void testRemoveCommentFromArticle() {

		assertEquals(0, article.getNumberOfComments());

		assertTrue(commentService.addComment(commentByUnknown, article));

		assertEquals(1, article.getNumberOfComments());

		assertTrue(commentService.removeComment(commentByUnknown, article));

		assertEquals(0, article.getNumberOfComments());
	}

	@Test
	public void testRemoveCommentFromArticleFailParametersMustNotBeNull() {

		assertFalse(commentService.removeComment(null, article));

		assertFalse(commentService.removeComment(commentByUnknown, null));

		assertFalse(commentService.removeComment(null, null));
	}

	@Test
	public void testFindAllComments() {

		Set<Comment> comments = commentService.findAllComments(article);

		assertEquals(0, comments.size());

		assertTrue(commentService.addComment(commentByUser, article));

		comments = commentService.findAllComments(article);

		assertEquals(1, comments.size());

	}
}
