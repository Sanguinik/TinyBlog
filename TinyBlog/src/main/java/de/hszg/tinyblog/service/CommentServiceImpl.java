package de.hszg.tinyblog.service;

import java.util.Set;

import de.hszg.tinyblog.persistence.CommentDao;
import de.hszg.tinyblog.persistence.CommentDaoJpa;
import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;

/**
 * This is the implementation of the CommentService.
 * 
 * @author marlene
 * 
 */

public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao = new CommentDaoJpa();

	@Override
	public boolean addComment(final Comment comment, final Article article) {

		if (commentDao.addComment(comment, article)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean removeComment(final Comment comment, final Article article) {

		if (commentDao.removeComment(comment, article)) {
			return true;
		}

		return false;
	}

	@Override
	public Set<Comment> findAllComments(final Article article) {

		return commentDao.findAllComments(article);
	}

}
