package de.hszg.tinyblog.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;

import de.hszg.tinyblog.model.Article;

@SessionScoped
public class ArticleServiceMockImpl implements ArticleService {

	private List<Article> articles = new ArrayList<Article>();
	
	@Override
	public boolean addArticle(Article article) {
		
		if(article == null){
			throw new IllegalArgumentException("Paramater article must not be null.");
		}
		
		if(articles.contains(article)){
			return false;
		}

		articles.add(article);
		
		return true;
	}

	@Override
	public List<Article> findAllArticles() {

		return articles;
	}

}
