package de.hszg.tinyblog.persistence;

import static de.hszg.tinyblog.util.Validator.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.util.EmfFactory;

/**
 * This is the JPA implementation for the ArticleDao.
 * 
 * @author marlene
 * 
 */

public class ArticleDaoJpa implements ArticleDao {

	private EntityManagerFactory emf = EmfFactory.getInstance();

	@Override
	public boolean addArticle(final Article article) {

		if (nullCheck(article)) {

			long foundId = article.getId();
			Article foundArticle = findArticleById(foundId);
			if (foundArticle != null) {
				return false;
			}

			EntityManager entityManager = emf.createEntityManager();

			entityManager.getTransaction().begin();
			entityManager.persist(article);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;

		}

		return false;

	}

	@Override
	public boolean removeArticle(final Article article) {

		if (nullCheck(article)) {

			EntityManager entityManager = emf.createEntityManager();
			Article foundArticle = entityManager.find(Article.class,
					article.getId());
			entityManager.getTransaction().begin();
			entityManager.remove(foundArticle);
			entityManager.getTransaction().commit();
			entityManager.close();

			return true;
		}
		return false;
	}

	@Override
	public boolean editArticle(final Article article) {
		if (nullCheck(article)) {

			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(article);
			entityManager.getTransaction().commit();
			entityManager.close();

			return true;
		}
		return false;
	}

	@Override
	public Article findArticleById(final long id) {
		EntityManager entityManager = emf.createEntityManager();
		Article foundArticle = entityManager.find(Article.class, id);
		entityManager.close();
		return foundArticle;
	}

	@Override
	public List<Article> findAllArticles() {

		EntityManager entityManager = emf.createEntityManager();
		TypedQuery<Article> q = entityManager.createQuery(
				"select a from Article a", Article.class);
		List<Article> articles = new ArrayList<Article>();
		articles = q.getResultList();
		entityManager.close();
		return articles;
	}

	private boolean nullCheck(final Article article) {

		if (article == null) {
			return false;
		} else {

			return checkNotNull(article.getTitle(), article.getContent(),
					article.getUser());

		}
	}

}
