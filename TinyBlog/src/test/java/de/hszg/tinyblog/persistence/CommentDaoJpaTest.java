package de.hszg.tinyblog.persistence;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;
import de.hszg.tinyblog.persistence.model.User;

public class CommentDaoJpaTest {
	
	private static final String ARTICLE_CONTENT = "Content";
	private static final String TITLE = "Title";
	private static final String NAME = "John Doe";
	private static final String EMAIL = "John@example.org";
	private static final String CONTENT = "What a great article!";
	private static final String OTHER_CONTENT = "Thank you for the compliment!";
	private static final long RANDOM_ID = 42;
	private CommentDao commentDao;
	private User user = new User("Marlene","secret","Marlene@example.org");
	private Article article = new Article(TITLE, ARTICLE_CONTENT, user);
	private EntityManagerFactory emf;

	@Before
	public void setUp(){
		commentDao = new CommentDaoJpa();
		emf = ((CommentDaoJpa)commentDao).emf;
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.persist(article);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	@After
	public void tearDown(){
		emf.close();
	}
	
	@Test
	public void testAddCommentByUnknownUser() {
		Comment comment = new Comment(NAME, EMAIL, CONTENT);
		assertTrue(commentDao.addComment(comment, article));
		
		EntityManager entityManager = emf.createEntityManager();
		//refresh the article
		article = entityManager.find(Article.class, article.getId());
		entityManager.close();
		Comment next = article.getComments().iterator().next();
		assertEquals(NAME, next.getName());
		assertEquals(EMAIL, next.getEmail());
		assertEquals(CONTENT, next.getContent());
		
	}
	
	@Test
	public void testAddCommentByRegisteredUser() {
		Comment comment = new Comment(user, CONTENT);
		assertTrue(commentDao.addComment(comment, article));
		
		EntityManager entityManager = emf.createEntityManager();
		//refresh the article and user
		article = entityManager.find(Article.class, article.getId());
		user = entityManager.find(User.class, user.getId());
		entityManager.close();
		Comment next = article.getComments().iterator().next();
		assertEquals(user.getName(), next.getUser().getName());
		assertEquals(user.getEmail(), next.getUser().getEmail());
		assertEquals(CONTENT, next.getContent());
	}
	
	@Test
	public void testAddMultipleComments(){
		Comment unknownUserComment = new Comment(NAME, EMAIL, CONTENT);
		Comment userComment = new Comment(user, OTHER_CONTENT);
		assertTrue(commentDao.addComment(unknownUserComment, article));
		
		EntityManager entityManager = emf.createEntityManager();
		article = entityManager.find(Article.class, article.getId());
		entityManager.close();
		assertEquals(1, article.getNumberOfComments());
		
		assertTrue(commentDao.addComment(userComment, article));
		assertEquals(2, article.getNumberOfComments());
	}
	
	@Test
	public void testAddSameCommentFail(){
		Comment comment = new Comment(NAME, EMAIL, CONTENT);
		assertTrue(commentDao.addComment(comment, article));
		
		EntityManager entityManager = emf.createEntityManager();
		article = entityManager.find(Article.class, article.getId());
		entityManager.close();
		assertEquals(1, article.getNumberOfComments());
		
		assertFalse(commentDao.addComment(comment, article));
		assertEquals(1, article.getNumberOfComments());
		}
	
	@Test
	public void testAddCommentFailCommentMustNotBeNull(){
		assertFalse(commentDao.addComment(null, article));
	}
	
	@Test
	public void testAddCommentFailArticleMustNotBeNull(){
		Comment comment = new Comment(NAME, EMAIL, CONTENT);
		assertFalse(commentDao.addComment(comment, null));
		
		Comment comment2 = new Comment(user, CONTENT);
		assertFalse(commentDao.addComment(comment2, null));
	}
	
	@Test
	public void testAddCommentFailCommentParametersMustNotBeNull(){
		Comment comment = new Comment(null, EMAIL, CONTENT);
		assertFalse(commentDao.addComment(comment, article));
		
		comment = new Comment(NAME, null, CONTENT);
		assertFalse(commentDao.addComment(comment, article));
		
		comment = new Comment(NAME, EMAIL, null);
		assertFalse(commentDao.addComment(comment, article));
		
		comment = new Comment(null, CONTENT);
		assertFalse(commentDao.addComment(comment, article));
		
		comment = new Comment(user, null);
		assertFalse(commentDao.addComment(comment, article));
				
	}
	
	@Test
	public void testAddCommentFailNoPersistedArticle(){
		Article article2 = new Article(TITLE, ARTICLE_CONTENT, user);
		Comment comment = new Comment(NAME, EMAIL, CONTENT);
		assertFalse(commentDao.addComment(comment, article2));
	}
	
	@Test
	public void testAddCommentOnlyOneArticleWithComment(){
		Article article2 = new Article(TITLE, ARTICLE_CONTENT, user);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(article2);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		assertEquals(0, article.getNumberOfComments());
		assertEquals(0, article2.getNumberOfComments());
		
		Comment comment = new Comment(user, CONTENT);
		assertTrue(commentDao.addComment(comment, article));
		
		entityManager = emf.createEntityManager();
		article = entityManager.find(Article.class, article.getId());
		article2 = entityManager.find(Article.class, article2.getId());
		entityManager.close();
		
		assertEquals(1, article.getNumberOfComments());
		assertEquals(0, article2.getNumberOfComments());
		
	}
	

	@Test
	public void testRemoveComment() {
		Comment comment = new Comment(NAME, EMAIL, CONTENT);
		
		//refresh article
		EntityManager entityManager = emf.createEntityManager();
		article = entityManager.find(Article.class, article.getId());
		entityManager.close();

		assertEquals(0, article.getNumberOfComments());
		assertTrue(commentDao.addComment(comment, article));
		
		assertEquals(1, article.getNumberOfComments());
		assertTrue(commentDao.removeComment(comment, article));
		assertEquals(0, article.getNumberOfComments());
	}
	
	@Test
	public void testRemoveCommentFailCommentMustNotBeNull(){
		assertFalse(commentDao.removeComment(null, article));
	}
	
	@Test
	public void testRemoveCommentFailArticleMustNotBeNull(){
		Comment comment = new Comment(NAME, EMAIL, CONTENT);
		assertTrue(commentDao.addComment(comment, article));
		assertFalse(commentDao.removeComment(comment, null));
		
		Comment comment2 = new Comment(user, CONTENT);
		assertTrue(commentDao.addComment(comment2, article));
		assertFalse(commentDao.removeComment(comment2, null));
	}
	
	@Test
	public void testFindCommentById(){
		
		Comment comment = new Comment(NAME, EMAIL, CONTENT);
		assertTrue(commentDao.addComment(comment, article));
		long commentId = comment.getId();
		
		Comment foundComment = commentDao.findCommentById(commentId);
		
		assertEquals(NAME, foundComment.getName());
		assertEquals(EMAIL, foundComment.getEmail());
		assertEquals(CONTENT, foundComment.getContent());
		
	}
	
	@Test
	public void testFindCommentByIdFailNoPersistedComment(){
		Comment foundComment = commentDao.findCommentById(RANDOM_ID);
		assertNull(foundComment);
	}
	
	@Test
	public void testFindCommentByIdFailIdNotAvailable(){
		Comment comment = new Comment(NAME, EMAIL, CONTENT);
		assertTrue(commentDao.addComment(comment, article));
		long commentId = comment.getId();
		long newId = commentId + 1;
		assertNull(commentDao.findCommentById(newId));
	}

}
