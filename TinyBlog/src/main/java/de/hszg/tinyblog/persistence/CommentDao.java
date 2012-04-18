package de.hszg.tinyblog.persistence;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;

public interface CommentDao {
	
	/**
	* this method adds a comment to an article in the database
	*/
	boolean addComment(Comment comment, Article article);
	
	/**
	* this method removes an comment from the database
	*/
	boolean removeComment(Comment comment);

}
