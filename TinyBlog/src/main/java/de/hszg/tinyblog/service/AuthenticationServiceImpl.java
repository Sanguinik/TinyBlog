package de.hszg.tinyblog.service;

import de.hszg.tinyblog.persistence.UserDao;
import de.hszg.tinyblog.persistence.UserDaoJpa;
import de.hszg.tinyblog.persistence.model.User;

public class AuthenticationServiceImpl implements AuthenticationService {

	private UserDao userDao = new UserDaoJpa();

	@Override
	public boolean checkPassword(final String email, final String password) {

		if (nullCheck(email, password)) {

			User user = userDao.findUserByEmail(email);

			if (nullUserCheck(user)) {
				if (user.getPassword().equals(password)) {

					return true;
				}
			}

		}

		return false;
	}

	private boolean nullCheck(final String email, final String password) {
		if (email == null || password == null) {
			return false;
		}
		return true;

	}

	private boolean nullUserCheck(final User user) {
		if (user == null) {
			return false;
		}
		return true;
	}

}
