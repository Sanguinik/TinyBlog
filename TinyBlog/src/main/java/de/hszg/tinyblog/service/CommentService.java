package de.hszg.tinyblog.service;

import de.hszg.tinyblog.model.Article;
import de.hszg.tinyblog.model.Comment;

public interface CommentService {
	
	/**
	* this method adds a comment to an article in the database
	*/
	boolean addComment(Comment comment, Article article);
	
	/**
	* this method removes an comment from the database
	*/
	boolean removeComment(Comment comment);

}
