package de.hszg.tinyblog.persistence;

import java.util.List;

import de.hszg.tinyblog.persistence.model.Article;

/**
 * 
 * @author marlene
 * 
 */

public interface ArticleDao {

	/**
	 * This method adds a new article to the database.
	 * 
	 * @param article
	 *            The article which should be added.
	 * @return true if the article was added successful.
	 */
	boolean addArticle(Article article);

	/**
	 * This method removes an given article from the database.
	 * 
	 * @param article
	 *            The article which should be removed.
	 * @return true if the article was removed successful.
	 */
	boolean removeArticle(Article article);

	/**
	 * This method saves the changes of an edited article.
	 * 
	 * @param article
	 *            The article which should be saved.
	 * @return true if the article was edited successful.
	 */
	boolean editArticle(Article article);

	/**
	 * This method gets an article by its id.
	 * 
	 * @param id
	 *            The of the article to be found.
	 * @return The article with the given id.
	 */
	Article findArticleById(long id);

	/**
	 * This method returns all existing articles.
	 * 
	 * @return A list of all existing articles.
	 */
	List<Article> findAllArticles();

}
