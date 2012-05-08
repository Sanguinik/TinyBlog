package de.hszg.tinyblog.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.service.ArticleService;
import de.hszg.tinyblog.service.ArticleServiceImpl;

/**
 * This managed bean is responsible to hold the data of an single article within
 * a request of the user.
 * 
 * @author marlene
 * 
 */

@SessionScoped
@ManagedBean
public class FullArticleBean {

	private Article article;

	/**
	 * Empty constructor is needed for JSF
	 */
	public FullArticleBean() {
	}

	/**
	 * This method is responsible for getting the article the user want to show
	 * completely.
	 * 
	 * @param article
	 *            The article which is requested by the user.
	 * @return fullArticle as the direction to the full article.
	 */
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
