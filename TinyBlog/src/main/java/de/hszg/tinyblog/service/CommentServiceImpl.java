package de.hszg.tinyblog.service;

import de.hszg.tinyblog.persistence.CommentDao;
import de.hszg.tinyblog.persistence.CommentDaoJpa;
import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;

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

}
