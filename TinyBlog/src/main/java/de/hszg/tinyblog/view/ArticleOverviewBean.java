package de.hszg.tinyblog.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.service.ArticleService;
import de.hszg.tinyblog.service.ArticleServiceImpl;
import de.hszg.tinyblog.util.ArticleDateComparator;

/**
 * This managed bean is responsible for getting all articles for the view.
 * 
 * @author marlene
 * 
 */

@ManagedBean
public class ArticleOverviewBean {

	private static final int PREVIEW_LENGTH = 200;
	private List<Article> articleList = new ArrayList<Article>();

	/**
	 * The constructor creates a new articleService and get the actual list of
	 * articles from it.
	 */
	public ArticleOverviewBean() {
		ArticleService articleService = new ArticleServiceImpl();
		articleList = articleService.findAllArticles();
	}

	/**
	 * This getter sorts the actual list of articles with the latest article on
	 * top.
	 * 
	 * @return articleList a sorted list with the latest article on top.
	 */
	public List<Article> getArticleList() {

		Comparator<Article> comp = new ArticleDateComparator();
		Comparator<Article> reverse = Collections.reverseOrder(comp);
		Collections.sort(articleList, reverse);

		return articleList;
	}

	/**
	 * This method creates the preview for the given Article.
	 * 
	 * @param article
	 *            The article where the content should be shorten.
	 * @return The shorten content of the article.
	 */

	public String articlePreview(final Article article) {

		String content = article.getContent();

		if (content.length() > PREVIEW_LENGTH) {

			content = content.substring(0, PREVIEW_LENGTH - 1);

			content = content.concat(" [...]");
		}

		return content;
	}

}
