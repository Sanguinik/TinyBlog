package de.hszg.tinyblog.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.service.ArticleService;
import de.hszg.tinyblog.service.ArticleServiceImpl;

/**
 * This managed bean provides the methods for editing and removing an article
 * from the database.
 * 
 * @author marlene
 * 
 */

@SessionScoped
@ManagedBean
public class EditArticleBean {

	private String title;
	private String content;

	private Article article;
	private ArticleService articleService = new ArticleServiceImpl();

	/**
	 * Empty constructor for JSF.
	 */
	public EditArticleBean() {
	}

	/**
	 * This method saves the changes made to an article.
	 * 
	 * @return index if the article was changed successfully or null if it
	 *         fails.
	 */
	public String editArticle() {

		if (articleService.editArticle(article)) {
			return "index";
		}

		return null;
	}

	/**
	 * This method deletes the selected article.
	 * 
	 * @return index if the article was deleted successfully or null if it
	 *         fails.
	 */
	public String removeArticle() {

		if (articleService.removeArticle(getArticle())) {
			return "index";
		}

		return null;
	}

	/**
	 * This method leads to the edit article page and provides the wanted
	 * article.
	 * 
	 * @param article
	 *            The article that should be edited.
	 * 
	 * @return editArticle to lead to the edit article page.
	 */
	public String showEditArticle(final Article article) {

		long articleId = article.getId();
		this.article = articleService.findArticleById(articleId);
		return "editArticle";
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(final Article article) {
		this.article = article;
	}

}
