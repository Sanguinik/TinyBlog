package de.hszg.tinyblog.service;

import java.util.List;

import de.hszg.tinyblog.persistence.ArticleDao;
import de.hszg.tinyblog.persistence.ArticleDaoJpa;
import de.hszg.tinyblog.persistence.model.Article;

/**
 * This is the implementation of the ArticleService.
 * 
 * @author marlene
 * 
 */

public class ArticleServiceImpl implements ArticleService {

	private ArticleDao articleDao = new ArticleDaoJpa();

	@Override
	public List<Article> findAllArticles() {

		List<Article> articles = articleDao.findAllArticles();

		return articles;
	}

	@Override
	public boolean addArticle(final Article article) {

		if (articleDao.addArticle(article)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean removeArticle(final Article article) {

		if (articleDao.removeArticle(article)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean editArticle(final Article article) {

		if (articleDao.editArticle(article)) {
			return true;
		}
		return false;
	}

	@Override
	public Article findArticleById(final long articleId) {
		return articleDao.findArticleById(articleId);
	}
}
