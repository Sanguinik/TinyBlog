package de.hszg.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 
 * This is the model class for an article.
 * 
 * @author marlene
 *
 */
public class Article {

	private String title;
	private String content;
	private Date publishingDate;
	private Set<Comment> comments;
	private User user;

	/**
	 * This constructor is used to create a new article.
	 * 
	 * @param title The title of the article.
	 * @param content The text of the article.
	 * @param user The user who wrote the article.
	 */

	public Article(String title, String content, User user) {
		publishingDate = new Date();
		comments = new HashSet<Comment>();
		this.title = title;
		this.content = content;
		this.user = user;
	}

	/**
	 * This method adds a comment to a Set of Comments.
	 * @param comment The comment which should be added.
	 */
	public void addComment(Comment comment) {
		comments.add(comment);
	}

	/**
	 * This method removes a comment from a Set of Comments.
	 * @param comment The comment which should be removed.
	 */
	public void removeComment(Comment comment) {
		comments.remove(comment);
	}

	/**
	 * 
	 * @return unmodifiable Set of comments
	 */
	public Set<Comment> getComments() {

		return Collections.unmodifiableSet(comments);

	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}
}
