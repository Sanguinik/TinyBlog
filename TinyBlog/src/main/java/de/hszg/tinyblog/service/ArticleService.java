package de.hszg.tinyblog.service;

import java.util.List;

import de.hszg.tinyblog.persistence.model.Article;

/**
 * 
 * This service provides all methods for the database interaction for the
 * articles.
 * 
 * @author marlene
 * 
 */

public interface ArticleService {

	/**
	 * This method returns all articles saved in the database.
	 * 
	 * @return A list with all articles from the database.
	 */
	List<Article> findAllArticles();

	/**
	 * This method gives the article object to the the persistence layer to
	 * persist it into the database.
	 * 
	 * @param article2
	 *            The article should be persisted.
	 * @return true if the article was added successful and false if not.
	 */
	boolean addArticle(Article article);

	/**
	 * This method gives the article object to the persistence layer to remove
	 * it from the database.
	 * 
	 * @param article
	 *            The article should be removed.
	 * @return true if the article was removed successful.
	 */
	boolean removeArticle(Article article);

	/**
	 * This method gives the article object to the persistence layer to save the
	 * changes made to it.
	 * 
	 * @param article
	 *            The article which was edited.
	 * @return true if the articles data was changed correctly in the database.
	 */
	boolean editArticle(Article article);

	/**
	 * This method gets an article out of the database by its id.
	 * 
	 * @param articleId
	 *            The id of the article.
	 * @return The article was found with the given id.
	 */
	Article findArticleById(long articleId);

}
