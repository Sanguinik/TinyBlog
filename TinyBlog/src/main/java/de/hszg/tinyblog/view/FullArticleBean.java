package de.hszg.tinyblog.view;

import javax.faces.bean.ManagedBean;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.service.ArticleService;
import de.hszg.tinyblog.service.ArticleServiceImpl;

@ManagedBean
public class FullArticleBean {

	private Article article;

	/**
	 * Empty constructor is needed for JSF
	 */
	public FullArticleBean() {

	}

	public String showFullArticle(final Article article) {
		long articleId = article.getId();
		ArticleService articleService = new ArticleServiceImpl();
		this.article = articleService.findArticleById(articleId);
		return "fullArticle";
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(final Article article) {
		this.article = article;
	}

}
