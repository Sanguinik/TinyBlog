package de.hszg.tinyblog.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.User;

/**
 * This class sets an user into the database when the application starts and
 * deletes it, when the application shuts down.
 * 
 * @author marlene
 * 
 */
@WebListener
public class UserCreator implements ServletContextListener {

	private EntityManagerFactory emf = EmfFactory.getInstance();

	@Override
	public void contextDestroyed(final ServletContextEvent arg0) {
		emf.close();
		System.out.println("AUF WIEDERSEHEN");
	}

	@Override
	public void contextInitialized(final ServletContextEvent arg0) {

		User user = new User("Admin", "admin", "admin@example.org");
		Article article = new Article(
				"My first article",
				"This is an example for an article. Have fun with your new blog.",
				user);

		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.persist(article);
		entityManager.getTransaction().commit();
		entityManager.close();

		System.out.println("GUTEN TAG");
	}

}
