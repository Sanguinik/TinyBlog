package de.hszg.tinyblog.persistence;

import java.util.ArrayList;
import java.util.List;

import de.hszg.tinyblog.persistence.model.Article;

public class ArticleDaoMock implements ArticleDao {
	
	private List<Article> articles;
	private Article article;
	
	public ArticleDaoMock(){
		articles = new ArrayList<Article>();
	}

	@Override
	public boolean addArticle(Article article) {	
		return articles.add(article);
	}

	@Override
	public boolean removeArticle(Article article) {
		return articles.remove(article);
	}

	@Override
	public Article setArticle(Article article) {
		
		return articles.set(0, article);
	}

	@Override
	public Article findArticleById(long id) {
	
		for(int i = 0; i<=articles.size(); i++){
			long foundId = articles.get(i).getId();
			if(foundId == id){
				return articles.get(i);
			}
			
		}
		return null;
	}

	@Override
	public List<Article> findAllArticles() {
		return articles;
	}

}
