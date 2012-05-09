package de.hszg.tinyblog.util;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;
import de.hszg.tinyblog.persistence.model.User;

public class SetupDataCreatorTest {

	@Test
	public void test() {

		EntityManagerFactory emf = EmfFactory.getInstance();
		EntityManager entityManager = emf.createEntityManager();

		TypedQuery<User> q = entityManager.createQuery("select u from User u",
				User.class);
		TypedQuery<Article> qa = entityManager.createQuery(
				"select a from Article a", Article.class);
		TypedQuery<Comment> qc = entityManager.createQuery(
				"select c from Comment c", Comment.class);

		assertEquals(0, q.getResultList().size());
		assertEquals(0, qa.getResultList().size());
		assertEquals(0, qc.getResultList().size());

		SetupDataCreator setUpDataCreator = new SetupDataCreator();
		setUpDataCreator.contextInitialized(null);

		assertEquals(1, q.getResultList().size());
		assertEquals(1, qa.getResultList().size());
		assertEquals(1, qc.getResultList().size());

		setUpDataCreator.contextDestroyed(null);

	}

}
