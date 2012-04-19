package de.hszg.tinyblog.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.User;

public class ArticleDaoJPATest {

	private static final String MY_CONTENT = "My content.";
	private static final String MY_TITLE = "My title";
	private static final String MY_OTHER_TITLE = "My other title";
	private static final String MY_OTHER_CONTENT = "My other content";
	private long id;
	private ArticleDao articleDao;
	private User user = new User("Marlene", "secret", "marlene@example.org");
	private EntityManagerFactory emf;

	@Before
	public void setUp() {
		articleDao = new ArticleDaoJpa();
		//get the EntityManagerFactory from ArticleDaoJpa
		emf = ((ArticleDaoJpa) articleDao).emf;
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		}
	
	@After
	public void tearDown(){
		emf.close();
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
	public void testRemoveArticle(){
		Article article = new Article(MY_TITLE, MY_CONTENT, user);
		
		assertTrue(articleDao.addArticle(article));
		
		assertEquals(1, articleDao.findAllArticles().size());
		
		assertTrue(articleDao.removeArticle(article));
		
		assertEquals(0,articleDao.findAllArticles().size());
	}
}
