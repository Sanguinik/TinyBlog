package de.hszg.tinyblog.view;

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

	// TODO: Managed Property einbinden
	private Article article;

	public CommentBean() {
		CommentService commentService = new CommentServiceImpl();
		comments = commentService.findAllComments(article);
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
