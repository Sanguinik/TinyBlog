package de.hszg.tinyblog.service;

import java.util.List;

import de.hszg.tinyblog.model.Article;

public interface ArticleService {
	
	boolean addArticle(Article article);

	List<Article> findAllArticles();
	
}
