package de.hszg.tinyblog.persistence;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.junit.Test;

import de.hszg.tinyblog.persistence.model.User;

public class UserCreatorTest {

	@Test
	public void test() {

		EntityManagerFactory emf = EmfFactory.getInstance();
		EntityManager entityManager = emf.createEntityManager();

		TypedQuery<User> q = entityManager.createQuery("select u from User u",
				User.class);

		assertEquals(0, q.getResultList().size());

		UserCreator userCreator = new UserCreator();
		userCreator.contextInitialized(null);

		assertEquals(1, q.getResultList().size());

	}

}
