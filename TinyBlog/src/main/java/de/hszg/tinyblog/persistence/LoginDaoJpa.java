package de.hszg.tinyblog.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.hszg.tinyblog.persistence.model.User;

public class LoginDaoJpa implements LoginDao {

	private static final String PERSISTENCE_UNIT = "testdb";
	//default scope for testing purposes, there should be only one EntityManagerFactory
	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT);
	
	@Override
	public boolean login(User user, String password) {

		if(nullCheck(user, password)){
			
		
		EntityManager entityManager = emf.createEntityManager();
		user = entityManager.find(User.class, user.getId());
		entityManager.close();
		if(user.getPassword() == password){
			entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(user);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		}
		
		}
		return false;
	}

	@Override
	public boolean logout(User user) {
		
		
		return false;
	}

	private boolean nullCheck(User user, String password){
		if(user == null || password == null){
			return false;
		}
		return true;
		
	}
	
}
