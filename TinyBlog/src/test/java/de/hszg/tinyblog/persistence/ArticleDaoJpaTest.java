package de.hszg.tinyblog.persistence;

import static org.junit.Assert.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.User;

public class ArticleDaoJpaTest {

	private static final String MY_CONTENT = "My content.";
	private static final String MY_TITLE = "My title";
	private static final String MY_OTHER_TITLE = "My other title";
	private static final String MY_OTHER_CONTENT = "My other content";
	private static final long RANDOM_ID = 42;
	private long id;
	private ArticleDao articleDao;
	private User user = new User("Marlene", "secret", "marlene@example.org");
	private EntityManagerFactory emf;

	@Before
	public void setUp() {
		articleDao = new ArticleDaoJpa();
		emf = EmfFactory.getInstance();
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		}
	
	@After
	public void tearDown(){
		emf.close();
		EmfFactory.reset();
	}

	@Test
	public void testAddArticle() {

		EntityManager entityManager = emf.createEntityManager();

		Article article;

		entityManager.getTransaction().begin();

		article = new Article(MY_TITLE, MY_CONTENT, user);

		entityManager.persist(article);
		entityManager.getTransaction().commit();

		Query q = entityManager.createQuery("select a from Article a");

		Article foundArticle = (Article) q.getSingleResult();

		assertEquals(MY_TITLE, foundArticle.getTitle());
		assertEquals(MY_CONTENT, foundArticle.getContent());

		entityManager.clear();

	}

	@Test
	public void testAddArticleJpa() {
		
		
		Article article = new Article(MY_TITLE, MY_CONTENT, user);

		
		assertTrue(articleDao.addArticle(article));
		id = article.getId();
		
		EntityManager entityManager = emf.createEntityManager();
		Article foundArticle = entityManager.find(Article.class, id);
		entityManager.close();
		assertEquals(MY_TITLE, foundArticle.getTitle());
		assertEquals(MY_CONTENT, foundArticle.getContent());
		
	}
	
	
	@Test
	public void testAddMultipleArticles(){
		
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		
		assertTrue(articleDao.addArticle(article));
		assertEquals(1, articleDao.findAllArticles().size());
				
		Article article2 = new Article(MY_OTHER_TITLE, MY_OTHER_CONTENT, user);
		assertTrue(articleDao.addArticle(article2));
		assertEquals(2, articleDao.findAllArticles().size());
	}

	
	@Test
	public void testAddSameArticleFail(){
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		
		assertTrue(articleDao.addArticle(article));
		
		assertEquals(1, articleDao.findAllArticles().size());
				
		assertFalse(articleDao.addArticle(article));
		
		assertEquals(1, articleDao.findAllArticles().size());	
			
	}
	
	@Test
	public void testAddArticleFailNullParameters(){
		Article article = new Article(null, MY_CONTENT, user);
		assertFalse(articleDao.addArticle(article));
		
		article = new Article(MY_CONTENT, null, user);
		assertFalse(articleDao.addArticle(article));
		
		article = new Article(MY_CONTENT, MY_CONTENT, null);
		assertFalse(articleDao.addArticle(article));
	}
	
	@Test
	public void testAddArticleFailArticleMustNotBeNull(){
		assertFalse(articleDao.addArticle(null));
	}
	
	@Test
	public void testRemoveArticle(){
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		
		assertTrue(articleDao.addArticle(article));
		
		assertEquals(1, articleDao.findAllArticles().size());
		
		assertTrue(articleDao.removeArticle(article));
		
		assertEquals(0,articleDao.findAllArticles().size());
	}
	
	@Test
	public void testRemoveArticleFailArticleMustNotBeNull(){
		assertFalse(articleDao.removeArticle(null));
	}

	@Test
	public void testEditArticle(){
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		
		assertTrue(articleDao.addArticle(article));
		
		id = article.getId();
		
		article.setTitle(MY_OTHER_TITLE);
		article.setContent(MY_OTHER_CONTENT);
		
		assertTrue(articleDao.editArticle(article));
		
		EntityManager entityManager = emf.createEntityManager();
		Article foundArticle = entityManager.find(Article.class, id);
		entityManager.close();
		assertEquals(MY_OTHER_TITLE, foundArticle.getTitle());
		assertEquals(MY_OTHER_CONTENT, foundArticle.getContent());
	}
	
	@Test
	public void testEditArticleFailArticleMustNotBeNull(){
		
		assertFalse(articleDao.editArticle(null));
	}
	
	@Test
	public void testEditArticleFailNullParameters(){
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		assertTrue(articleDao.addArticle(article));
		
		article.setTitle(null);
		assertFalse(articleDao.editArticle(article));
		
		article.setContent(null);
		assertFalse(articleDao.editArticle(article));
		
	}
	
	@Test
	public void testFindArticleById(){
		
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		
		assertTrue(articleDao.addArticle(article));
		id = article.getId();
		
		Article foundArticle = articleDao.findArticleById(id);
		
		assertEquals(MY_TITLE, foundArticle.getTitle());
		assertEquals(MY_CONTENT, foundArticle.getContent());
	}
	
	@Test
	public void testFindArticleByIdFailNoArticlePersisted(){
		
		Article foundArticle = articleDao.findArticleById(RANDOM_ID);
		assertNull(foundArticle);
	}
	
	@Test
	public void testFindArticleByIdFailIdNotAvailable(){
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		long articleId = article.getId();
		long newId = articleId + 1;
		assertNull(articleDao.findArticleById(newId));
	}
	
	
	
	
	
}
