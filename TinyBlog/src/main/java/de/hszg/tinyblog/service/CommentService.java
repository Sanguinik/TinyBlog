package de.hszg.tinyblog.service;

import java.util.Set;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;

/**
 * This is the interface for the CommentService. It provides the methods for the
 * interaction with comments.
 * 
 * @author marlene
 * 
 */

public interface CommentService {

	/**
	 * This method interacts with the persistence layer for adding comments.
	 * 
	 * @param article
	 *            The article where the comment should be added.
	 * @param comment
	 *            The comment to be added.
	 * @return true if the comment was added successful.
	 */
	boolean addComment(Comment comment, Article article);

	/**
	 * This method interacts with the persistence layer for removing comments.
	 * 
	 * @param comment
	 *            The comment which should be removed.
	 * @param article
	 *            The article where the comments should be removed.
	 * @return true if the comment was removed successful.
	 */
	boolean removeComment(Comment comment, Article article);

	/**
	 * This method gets all Comments of an Article.
	 * 
	 * @param article
	 *            The article where the comments should be loaded from.
	 * 
	 * @return A set of comments or null if there is no comment in the database.
	 */
	Set<Comment> findAllComments(Article article);

}
