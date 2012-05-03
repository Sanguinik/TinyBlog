package de.hszg.tinyblog.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * The EmfFactory is a factory for EntityManagerFactories. This is used to get
 * only one EntityManagerFactory at the same time in the running application.
 * 
 * @author marlene
 * 
 */

public final class EmfFactory {

	private static final String PERSISTENCE_UNIT_NAME = "testdb";
	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	private EmfFactory() {

	}

	/**
	 * This method returns an instance of an EntityManagerFactory.
	 * 
	 * @return An instance of an EntityManagerFactory.
	 */
	public static EntityManagerFactory getInstance() {
		return emf;
	}

	/**
	 * This method is mainly used for testing purposes. It resets the
	 * EntityManagerfactory to a new one.
	 */
	public static void reset() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

}
