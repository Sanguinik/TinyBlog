package de.hszg.tinyblog.persistence;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;

public class CommentDaoJpa implements CommentDao {
	
	private EntityManagerFactory emf = EmfFactory.getInstance();
	private ArticleDao articleDao = new ArticleDaoJpa();

	@Override
	public boolean addComment(Comment comment, Article article) {
		
		if(nullCheck(comment, article)){
			
		long foundArticleId = article.getId();
		Article foundArticle = articleDao.findArticleById(foundArticleId);
		if(foundArticle == null){
			return false;
		}
			
		long foundId = comment.getId();
		Comment foundComment = findCommentById(foundId);
		if(foundComment != null){
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
	public boolean removeComment(Comment comment, Article article) {
		
		if(nullCheck(comment, article)){
		
		article.removeComment(comment);
		articleDao.editArticle(article);
		
//		EntityManager entityManager = emf.createEntityManager();
//		comment = entityManager.find(Comment.class, comment.getId());
//		
//		
//		entityManager.getTransaction().begin();
//		entityManager.remove(comment);
//		entityManager.getTransaction().commit();
//		entityManager.close();
		return true;
		}
		return false;
	}

	@Override
	public Comment findCommentById(long id) {
		
		EntityManager entityManager = emf.createEntityManager();
		Comment comment = entityManager.find(Comment.class, id);
		
		return comment;
	}

	private boolean nullCheck(Comment comment, Article article){
		
		if(comment == null || article == null){
			return false;
		}
		
		if(comment.getUser() == null){
			if(comment.getName() == null || comment.getEmail() == null || comment.getContent() == null){
				return false;
			} 
			return true;		
		}else{
			if(comment.getUser().getName() == null || comment.getUser().getEmail() == null || comment.getContent() == null){
				return false;
			}
		}
		
		return true;
		
	}
	

}
	

