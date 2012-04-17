package de.hszg.tinyblog.service;

import de.hszg.tinyblog.model.User;

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
