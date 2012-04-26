package de.hszg.tinyblog.persistence;

import de.hszg.tinyblog.persistence.model.User;

public interface UserDao {

	/**
	 * This method finds an user by its email address.
	 * @param email The email address of the user.
	 * @return The user with the email address or null if there is no persisted user with
	 * 	the given email address, more than one user with the email address oder the given email 
	 * 	address is null itself. 
	 * 
	 */
	User findUserByEmail(String email);
	
}
