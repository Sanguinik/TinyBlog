package de.hszg.tinyblog.persistence;

import de.hszg.tinyblog.persistence.model.User;

public interface LoginDao {
	
	/**
	* This method logs a user with its correct password in.
	* @param user The User who do the login.
	* @param password The password is typed in by the user.
	* @return true if the password was correct.
	*/
	boolean login(User user, String password);

	/**
	* This method performs the logout for a user.
	* @param user The user which is actually logged in.
	* @return true if the logout was successful.
	*/
	boolean logout(User user);




}
