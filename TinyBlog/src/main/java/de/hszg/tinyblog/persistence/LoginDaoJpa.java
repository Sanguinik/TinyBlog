package de.hszg.tinyblog.persistence;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
