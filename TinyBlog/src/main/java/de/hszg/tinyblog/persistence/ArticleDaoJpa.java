package de.hszg.tinyblog.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import de.hszg.tinyblog.persistence.model.Article;

public class ArticleDaoJpa implements ArticleDao {

	
	private static final String PERSISTENCE_UNIT = "testdb";
	//default scope for testing purposes, there should be only one EntityManagerFactory
	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT);

	@Override
	public boolean addArticle(Article article) {
		
		if( nullCheck(article)){
			EntityManager entityManager = emf.createEntityManager();
			
			List<Article> articles = findAllArticles();
			for(Article a : articles){
				String foundTitle = a.getTitle();
				if(article.getTitle() == foundTitle){
					return false;
				}
			}

				entityManager.getTransaction().begin();
				entityManager.persist(article);
				entityManager.getTransaction().commit();
				entityManager.close();
				return true;
		}
		
		return false;


	}

	@Override
	public boolean removeArticle(Article article) {
		EntityManager entityManager = emf.createEntityManager();
		article = entityManager.find(Article.class, article.getId());
		entityManager.getTransaction().begin();
		entityManager.remove(article);
		entityManager.getTransaction().commit();
		entityManager.close();
			
		return true;
	}

	@Override
	public boolean editArticle(Article article) {
		if(nullCheck(article)){
			
		
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(article);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return true;
		}
		return false;
	}

	@Override
	public Article findArticleById(long id) {
		EntityManager entityManager = emf.createEntityManager();
		Article foundArticle = entityManager.find(Article.class, id);
		entityManager.close();
		return foundArticle;
	}

	@Override
	public List<Article> findAllArticles() {
		
		EntityManager entityManager = emf.createEntityManager();
		TypedQuery<Article> q = entityManager.createQuery("select a from Article a",Article.class);
		List<Article> articles = new ArrayList<Article>();
		articles = q.getResultList();
		
		return articles;
	}
	
	private boolean nullCheck(Article article){
		if(article == null){
			return false;
		}else{
			
			if(article.getTitle() == null || article.getContent() == null || article.getUser() == null){
				return false;
			}
			return true;
		}
	}

}
