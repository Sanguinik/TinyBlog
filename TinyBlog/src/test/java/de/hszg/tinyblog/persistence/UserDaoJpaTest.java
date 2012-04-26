package de.hszg.tinyblog.persistence;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.User;

public class UserDaoJpaTest {
	
	private static final String PASSWORD = "secret";
	private static final String USERNAME = "Marlene";
	private static final String CORRECT_EMAIL = "marlene@example.org";
	private static final String WRONG_EMAIL = "fail@example.org";
	private long userId;
	private UserDao userDao;
	private User user = new User(USERNAME, PASSWORD, CORRECT_EMAIL);
	private EntityManagerFactory emf;
	
	
	@Before
	public void setUp(){
		userDao = new UserDaoJpa();
		emf = ((UserDaoJpa)userDao).emf;
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		userId = user.getId();
	}
	
	@After
	public void tearDown(){
		emf.close();
	}

	@Test
	public void testFindUserByEmail() {
		User foundUser = userDao.findUserByEmail(CORRECT_EMAIL);
		assertEquals(USERNAME, foundUser.getName());
		assertEquals(userId, foundUser.getId());
		assertEquals(PASSWORD, foundUser.getPassword());
		
	}
	
	@Test
	public void testFindUserByEmailFailEmailMustNotBeNull(){
		assertNull(userDao.findUserByEmail(null));
	}
	
	@Test
	public void testFindUserByEmailFailEmailNotAvailable(){
		assertNull(userDao.findUserByEmail(WRONG_EMAIL));
	}
	
	@Test
	public void testFindUserByEmailFailMoreThanOneUserFound(){
		User user2 = new User(USERNAME, PASSWORD, CORRECT_EMAIL);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user2);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		assertNull(userDao.findUserByEmail(CORRECT_EMAIL));
	}

}
