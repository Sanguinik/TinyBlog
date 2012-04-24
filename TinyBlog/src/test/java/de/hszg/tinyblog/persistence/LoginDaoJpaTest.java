package de.hszg.tinyblog.persistence;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.User;

public class LoginDaoJpaTest {

	private static final String CORRECT_PASSWORD = "secret";
	private static final String WRONG_PASSWORD = "12345";
	private LoginDao loginDao;
	private User user = new User("Marlene", CORRECT_PASSWORD, "marlene@example.org");
	private EntityManagerFactory emf;
	
	@Before
	public void setUp(){
		loginDao = new LoginDaoJpa();
		emf = ((LoginDaoJpa)loginDao).emf;
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	
	@Test
	public void testLogin() {
		assertTrue(loginDao.login(user, CORRECT_PASSWORD));
		//refresh the user
		EntityManager entityManager = emf.createEntityManager();
		user = entityManager.find(User.class, user.getId());
		entityManager.close();
		assertTrue(user.isLoggedIn());
	}
	
	@Test
	public void testLoginFailWrongPassword(){
		assertFalse(loginDao.login(user, WRONG_PASSWORD));
		EntityManager entityManager = emf.createEntityManager();
		user = entityManager.find(User.class, user.getId());
		entityManager.close();
		assertFalse(user.isLoggedIn());
	}
	
	@Test
	public void testLoginFailUserMustNotBeNull(){
		assertFalse(loginDao.login(null, CORRECT_PASSWORD));
	}
	
	@Test
	public void testLoginFailPasswordMustNotBeNull(){
		assertFalse(loginDao.login(user, null));
	}

	@Test
	public void testLogout() {
		assertTrue(loginDao.login(user, CORRECT_PASSWORD));
		
		EntityManager entityManager = emf.createEntityManager();
		user = entityManager.find(User.class, user.getId());
		entityManager.close();
		
		assertTrue(user.isLoggedIn());
		
		assertTrue(loginDao.logout(user));
		
		assertFalse(user.isLoggedIn());
		
	}
	
	@Test
	public void testLogoutFailUserMustNotBeNull() {
		assertFalse(loginDao.logout(null));
	}
	
	@Test
	public void testLogoutFailNoUserLoggedIn(){
		EntityManager entityManager = emf.createEntityManager();
		user = entityManager.find(User.class, user.getId());
		entityManager.close();
		assertFalse(user.isLoggedIn());
		assertFalse(loginDao.logout(user));
	}


}
