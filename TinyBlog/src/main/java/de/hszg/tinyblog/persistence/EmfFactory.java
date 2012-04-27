package de.hszg.tinyblog.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfFactory {

	private static final String PERSISTENCE_UNIT_NAME = "testdb";
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public static EntityManagerFactory getInstance(){
		return emf;
	}
	
	/**
	 * This method is mainly used for testing purposes.
	 */
	public static void reset(){
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
}
