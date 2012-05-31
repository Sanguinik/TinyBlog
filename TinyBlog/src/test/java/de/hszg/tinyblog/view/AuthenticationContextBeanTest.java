package de.hszg.tinyblog.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.User;

public class AuthenticationContextBeanTest {

	private AuthenticationContextBean acb;
	private final static String EMAIL = "Email@email.com";
	private static final String PASSWORD = "secret";
	private static final User USER = new User("John Doe", PASSWORD, EMAIL);

	@Before
	public void setUp() {
		acb = new AuthenticationContextBean();
	}

	@Test
	public void testIsLoggedIn() {
		assertFalse(acb.isLoggedIn());
	}

	@Test
	public void testLogout() {
		acb.logout();
		assertFalse(acb.isLoggedIn());
	}

	@Test
	public void testSetAndGetEmail() {
		acb.setEmail(EMAIL);
		assertEquals(EMAIL, acb.getEmail());
	}

	@Test
	public void testSetAndGetPassword() {
		acb.setPassword(PASSWORD);
		assertEquals(PASSWORD, acb.getPassword());
	}

	@Test
	public void testSetAndGetUser() {
		acb.setUser(USER);
		assertEquals(USER, acb.getUser());
	}

}
