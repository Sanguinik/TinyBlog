package de.hszg.tinyblog.persistence;

import static de.hszg.tinyblog.util.Validator.checkNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;

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

			article.removeComment(comment);
			articleDao.editArticle(article);

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
