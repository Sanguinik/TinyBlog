package de.hszg.tinyblog.service;

public interface AuthenticationService {

	/**
	 * This method logs a user with its correct password in.
	 * 
	 * @param email
	 *            The email address of a user who wants to login.
	 * @param password
	 *            The password is typed in by the user.
	 * @return true if the password was correct.
	 */
	boolean checkPassword(String email, String password);

}
