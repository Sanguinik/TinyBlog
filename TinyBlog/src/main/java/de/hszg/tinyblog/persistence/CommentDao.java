package de.hszg.tinyblog.persistence;

import java.util.Set;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;

/**
 * This is the interface for the interaction with Comments on the database.
 * 
 * @author marlene
 * 
 */

public interface CommentDao {

	/**
	 * This method adds a comment to an article in the database.
	 * 
	 * @param comment
	 *            The comment which should be added.
	 * @param article
	 *            The article where the comment should be added.
	 * @return true if the transaction was successful.
	 */
	boolean addComment(Comment comment, Article article);

	/**
	 * This method removes an comment from the database.
	 * 
	 * @param comment
	 *            The comment which should be removed.
	 * @param article
	 *            The article where the comment should be removed.
	 * @return true if the transaction was successful.
	 */
	boolean removeComment(Comment comment, Article article);

	/**
	 * This method finds an comment by its id.
	 * 
	 * @param id
	 *            The id of the comment which should be found.
	 * @return The comment with the given id.
	 */
	Comment findCommentById(long id);

	/**
	 * This method returns all comments of an article.
	 * 
	 * @param article
	 *            The article where the comments should be loaded from.
	 * @return All comments of the given article.
	 */
	Set<Comment> findAllComments(Article article);

}
