package de.hszg.tinyblog.util;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.junit.Test;

import de.hszg.tinyblog.persistence.model.User;
import de.hszg.tinyblog.util.EmfFactory;
import de.hszg.tinyblog.util.SetupDataCreator;

public class SetupDataCreatorTest {

	@Test
	public void test() {

		EntityManagerFactory emf = EmfFactory.getInstance();
		EntityManager entityManager = emf.createEntityManager();

		TypedQuery<User> q = entityManager.createQuery("select u from User u",
				User.class);

		assertEquals(0, q.getResultList().size());

		SetupDataCreator userCreator = new SetupDataCreator();
		userCreator.contextInitialized(null);

		assertEquals(1, q.getResultList().size());

	}

}
