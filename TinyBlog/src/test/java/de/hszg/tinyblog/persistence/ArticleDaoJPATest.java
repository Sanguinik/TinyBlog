package de.hszg.tinyblog.persistence;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.User;

public class ArticleDaoJPATest {
	
	private static final String MY_CONTENT = "My content.";
	private static final String MY_TITLE = "My title";
	private static final String PERSISTENCE_UNIT = "testdb";
	private ArticleDao articleDao;
	private User user = new User("Marlene","secret","marlene@example.org");
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	
	@Before
	public void setUp(){
		articleDao = new ArticleDaoJpa();
		
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
		emf.close();
		
	}

}
