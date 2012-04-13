package de.hszg.tinyblog.service;

import de.hszg.tinyblog.model.Article;

/**
 *  
 * @author marlene
 *
 */

public interface ArticleService {

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
	Article saveArticle(Article article);
	
	/**
	 * This method gets an article by its id
	 * @param id The of the article to be found
	 */
	void findArticleById(long id);

	
}
