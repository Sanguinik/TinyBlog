package de.hszg.tinyblog.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;

import de.hszg.tinyblog.persistence.model.Article;
import de.hszg.tinyblog.persistence.model.Comment;
import de.hszg.tinyblog.service.CommentService;
import de.hszg.tinyblog.service.CommentServiceImpl;

/**
 * 
 * This is the managed bean for for showing and interacting with comments.
 * 
 * @author marlene
 * 
 */

@ManagedBean
public class CommentBean {

	private String name;
	private String content;
	private Set<Comment> comments;

	/**
	 * Empty constructor for JSF.
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
		CommentService commentService = new CommentServiceImpl();
		comments = commentService.findAllComments(article);
		List<Comment> commentList = new ArrayList<Comment>(comments);
		return commentList;
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

}
