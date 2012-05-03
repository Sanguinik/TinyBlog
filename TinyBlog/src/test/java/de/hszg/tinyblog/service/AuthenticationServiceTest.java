package de.hszg.tinyblog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.User;
import de.hszg.tinyblog.util.EmfFactory;

public class AuthenticationServiceTest {

	private static final String NAME = "Marlene";
	private static final String CORRECT_EMAIL = "marlene@example.org";
	private static final String CORRECT_PASSWORD = "secret";
	private static final String WRONG_EMAIL = "fail@example.org";
	private static final String WRONG_PASSWORD = "12345";
	private long userId;
	private AuthenticationService authenticationService;
	private User user = new User(NAME, CORRECT_PASSWORD, CORRECT_EMAIL);
	private EntityManagerFactory emf;

	@Before
	public void setUp() {
		emf = EmfFactory.getInstance();
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		authenticationService = new AuthenticationServiceImpl();
		userId = user.getId();

	}

	@After
	public void tearDown() {
		emf.close();
		EmfFactory.reset();
	}

	@Test
	public void testCheckPassword() {
		assertTrue(authenticationService.checkPassword(CORRECT_EMAIL,
				CORRECT_PASSWORD));
	}

	@Test
	public void testCheckPasswordFailWrongPassword() {
		assertFalse(authenticationService.checkPassword(CORRECT_EMAIL,
				WRONG_PASSWORD));
	}

	@Test
	public void testCheckPasswordFailWrongEmail() {
		assertFalse(authenticationService.checkPassword(WRONG_EMAIL,
				CORRECT_PASSWORD));
	}

	@Test
	public void testCheckPasswordFailEmailMustNotBeNull() {
		assertFalse(authenticationService.checkPassword(null, CORRECT_PASSWORD));
	}

	@Test
	public void testCheckPasswordFailPasswordMustNotBeNull() {
		assertFalse(authenticationService.checkPassword(CORRECT_EMAIL, null));
	}

	@Test
	public void testFindUserByEmail() {
		User foundUser = authenticationService.findUserByEmail(CORRECT_EMAIL);
		assertEquals(NAME, foundUser.getName());
		assertEquals(CORRECT_PASSWORD, foundUser.getPassword());
		assertEquals(userId, foundUser.getId());
	}

	@Test
	public void testFindUserByEmailFailParameterMustNotBeNull() {
		assertNull(authenticationService.findUserByEmail(null));
	}

	@Test
	public void testFindUserByEmailFailWrongEmail() {
		assertNull(authenticationService.findUserByEmail(WRONG_EMAIL));
	}

}
