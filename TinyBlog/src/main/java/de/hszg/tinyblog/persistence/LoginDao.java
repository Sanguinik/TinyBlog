package de.hszg.tinyblog.persistence;

import de.hszg.tinyblog.persistence.model.User;

public interface LoginDao {
	
	/**
	* This method logs a user with its correct password in
	*/
	boolean login(User user, String password);

	/**
	* This method performs the logout for a user
	*/
	boolean logout();

	/**
	* This method proves if a password is correct or not.
	*/
	boolean checkPassword(String password);


}
