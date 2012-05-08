package de.hszg.tinyblog.persistence;

import static de.hszg.tinyblog.util.Validator.checkNotNull;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;
import de.hszg.tinyblog.util.EmfFactory;

/**
 * This is the JPA implementation of the CommentDao.
 * 
 * @author marlene
 * 
 */

public class CommentDaoJpa implements CommentDao {

	private EntityManagerFactory emf = EmfFactory.getInstance();
	private ArticleDao articleDao = new ArticleDaoJpa();

	@Override
	public boolean addComment(final Comment comment, final Article article) {

		if (nullCheck(comment, article)) {

			long foundArticleId = article.getId();
			Article foundArticle = articleDao.findArticleById(foundArticleId);
			if (foundArticle == null) {
				return false;
			}

			long foundId = comment.getId();
			Comment foundComment = findCommentById(foundId);
			if (foundComment != null) {
				return false;
			}

			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(comment);
			entityManager.getTransaction().commit();
			entityManager.close();
			article.addComment(comment);
			articleDao.editArticle(article);

			return true;
		}
		return false;
	}

	@Override
	public boolean removeComment(final Comment comment, final Article article) {

		if (nullCheck(comment, article)) {

			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();
			Article attachedArticle = entityManager.find(Article.class,
					article.getId());
			Comment attachedComment = entityManager.find(Comment.class,
					comment.getId());
			attachedArticle.removeComment(attachedComment);
			entityManager.getTransaction().commit();
			entityManager.close();

			return true;
		}
		return false;
	}

	@Override
	public Comment findCommentById(final long id) {

		EntityManager entityManager = emf.createEntityManager();
		Comment comment = entityManager.find(Comment.class, id);

		return comment;
	}

	@Override
	public Set<Comment> findAllComments(final Article article) {

		long foundId = article.getId();
		Article foundArticle = articleDao.findArticleById(foundId);

		Set<Comment> comments = foundArticle.getComments();

		return comments;
	}

	private boolean nullCheck(final Comment comment, final Article article) {

		if (!checkNotNull(comment, article)) {
			return false;
		}

		if (comment.getUser() == null) {

			return checkNotNull(comment.getName(), comment.getEmail(),
					comment.getContent());

		} else {

			return checkNotNull(comment.getUser().getName(), comment.getUser()
					.getEmail(), comment.getContent());

		}

	}

}
