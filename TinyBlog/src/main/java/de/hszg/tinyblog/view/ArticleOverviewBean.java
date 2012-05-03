package de.hszg.tinyblog.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.service.ArticleService;
import de.hszg.tinyblog.service.ArticleServiceImpl;

/**
 * This managed bean is responsible for getting all articles for the view.
 * 
 * @author marlene
 * 
 */

@ManagedBean
public class ArticleOverviewBean {

	private List<Article> articleList = new ArrayList<Article>();

	/**
	 * The constructor creates a new articleService and get the actual list of
	 * articles from it.
	 */
	public ArticleOverviewBean() {
		ArticleService articleService = new ArticleServiceImpl();
		articleList = articleService.findAllArticles();
	}

	public List<Article> getArticleList() {
		return articleList;
	}

}
