package de.hszg.tinyblog.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.User;
import de.hszg.tinyblog.service.ArticleService;
import de.hszg.tinyblog.service.ArticleServiceImpl;

/**
 * This bean is used for creating a new article.
 * 
 * @author marlene
 * 
 */

@ManagedBean
public class CreateArticleBean {

	private String title;
	private String content;

	@ManagedProperty(value = "#{authenticationContextBean.user}")
	private User user;

	/**
	 * The empty constructor is needed for JSF.
	 */
	public CreateArticleBean() {

	}

	/**
	 * This method interacts with the user interface and the service methods for
	 * creating a new article to the database.
	 * 
	 * @return index for navigating on the index page when the article was
	 *         created successfully and null to stay on page if it fails.
	 */
	public String addArticle() {

		ArticleService articleService = new ArticleServiceImpl();

		Article article = new Article(title, content, getUser());

		if (articleService.addArticle(article)) {

			return "index";
		}

		return null;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
