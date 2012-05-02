package de.hszg.tinyblog.service;

import static de.hszg.tinyblog.util.Validator.checkNotNull;
import de.hszg.tinyblog.persistence.UserDao;
import de.hszg.tinyblog.persistence.UserDaoJpa;
import de.hszg.tinyblog.persistence.model.User;

public class AuthenticationServiceImpl implements AuthenticationService {

	private UserDao userDao = new UserDaoJpa();

	@Override
	public boolean checkPassword(final String email, final String password) {

		if (checkNotNull(email, password)) {

			User user = userDao.findUserByEmail(email);

			if (checkNotNull(user) && user.getPassword().equals(password)) {

				return true;
			}

		}

		return false;
	}

}
