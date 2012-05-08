package de.hszg.tinyblog.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;
import de.hszg.tinyblog.persistence.model.User;
import de.hszg.tinyblog.service.CommentService;
import de.hszg.tinyblog.service.CommentServiceImpl;

/**
 * 
 * This is the managed bean for for showing and interacting with comments.
 * 
 * @author marlene
 * 
 */

@SessionScoped
@ManagedBean
public class CommentBean {

	private static Logger logger = LoggerFactory.getLogger(CommentBean.class);

	private String name;
	private String email;
	private String content;
	private Set<Comment> comments;
	private CommentService commentService = new CommentServiceImpl();
	@ManagedProperty("#{authenticationContextBean.user}")
	private User user;
	@ManagedProperty("#{fullArticleBean}")
	private FullArticleBean fullArticleBean;

	/**
	 * Empty Constructor for JSF.
	 */
	public CommentBean() {
	}

	/**
	 * This method takes the set of comments from the given article and returns
	 * a list from the set.
	 * 
	 * @param article
	 *            The article where the comments should be loaded from.
	 * @return A list with the comments of the article.
	 */
	public List<Comment> showAllComments(final Article article) {
		comments = commentService.findAllComments(article);
		return new ArrayList<Comment>(comments);
	}

	/**
	 * This method creates communicates with the commentService to create a new
	 * comment to an given article.
	 * 
	 * @param article
	 *            The article where the comment should be added.
	 */
	public void addComment(final Article article) {

		Comment comment;

		if (user == null) {
			comment = new Comment(name, email, content);
		} else {
			comment = new Comment(user, content);
		}

		commentService.addComment(comment, article);
	}

	/**
	 * This method removes a comment from an article by communication with the
	 * commentService.
	 * 
	 * @param article
	 *            The article where the comment should be removed.
	 * @param comment
	 *            The comment which should be removed.
	 */
	public String removeComment(final Article article, final Comment comment) {

		FacesContext context = FacesContext.getCurrentInstance();

		logger.info("remove comment with article " + article + "and comment "
				+ comment);

		if (!commentService.removeComment(comment, article)) {
			context.addMessage(null,
					new FacesMessage("Das hat nicht geklappt."));
			logger.warn("remove comment fail");
		}
		fullArticleBean.showFullArticle(article);

		return null;

	}

	public String getName() {
		return name;
	}

	public void setName(final String title) {
		this.name = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(final Set<Comment> comments) {
		this.comments = comments;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public FullArticleBean getFullArticleBean() {
		return fullArticleBean;
	}

	public void setFullArticleBean(final FullArticleBean fullArticleBean) {
		this.fullArticleBean = fullArticleBean;
	}
}
