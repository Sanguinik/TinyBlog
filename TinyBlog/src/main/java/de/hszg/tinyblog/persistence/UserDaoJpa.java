package de.hszg.tinyblog.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import de.hszg.tinyblog.persistence.model.User;
import de.hszg.tinyblog.util.EmfFactory;

/**
 * This is the JPA implementation for the UserDao.
 * 
 * @author marlene
 * 
 */

public class UserDaoJpa implements UserDao {

	private EntityManagerFactory emf = EmfFactory.getInstance();

	@Override
	public User findUserByEmail(final String email) {
		if (email != null) {

			EntityManager entityManager = emf.createEntityManager();
			TypedQuery<User> q = entityManager.createQuery(
					"select u from User u where u.email = :email", User.class);

			List<User> users = q.setParameter("email", email).getResultList();
			entityManager.close();

			if (users.size() == 0) {
				return null;
			}
			if (users.size() == 1) {
				return users.get(0);
			}

		}
		return null;
	}

}
