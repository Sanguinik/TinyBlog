package de.hszg.tinyblog.view;

import java.util.ArrayList;
import java.util.List;

import de.hszg.tinyblog.persistence.model.Article;

public class ArticleOverviewBean {

	private List<Article> articleList = new ArrayList<Article>();

	public List<Article> getArticleList() {
		return articleList;
	}

}
