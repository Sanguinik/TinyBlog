package de.hszg.tinyblog.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class SetupDataCreator implements ServletContextListener {

	static Logger logger = LoggerFactory.getLogger(SetupDataCreator.class);
	private EntityManagerFactory emf = EmfFactory.getInstance();

	@Override
	public void contextDestroyed(final ServletContextEvent arg0) {
		emf.close();
		logger.info("Shut down, Servlet Context destroyed.");
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

		logger.info("Startup, Servlet Context initialized.");
	}

}
