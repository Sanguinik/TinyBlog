package de.hszg.tinyblog.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.service.ArticleService;
import de.hszg.tinyblog.service.ArticleServiceImpl;

@ManagedBean
public class ArticleOverviewBean {

	private ArticleService articleService;

	private List<Article> articleList = new ArrayList<Article>();

	public ArticleOverviewBean() {
		articleService = new ArticleServiceImpl();
		articleList = articleService.findAllArticles();
	}

	public List<Article> getArticleList() {
		return articleList;
	}

}
