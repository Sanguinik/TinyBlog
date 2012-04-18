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
	* This method adds a new article to the database
	* @param article The article which should be added
	*/
	boolean addArticle(Article article);
	
	/**
	* This method removes an given article from the database
	* @param article The article which should be removed
	*/
	boolean removeArticle(Article article);
	
	/**
	* This method saves the changes of an edited article
	* @param article The article which should be saved
	*/
	Article setArticle(Article article);
	
	/**
	 * This method gets an article by its id
	 * @param id The of the article to be found
	 * @return the article with the given id
	 */
	Article findArticleById(long id);
	
	/**
	 * This method returns all existing articles
	 * @return a list of all existing articles
	 */
	List<Article> findAllArticles();

	
}
