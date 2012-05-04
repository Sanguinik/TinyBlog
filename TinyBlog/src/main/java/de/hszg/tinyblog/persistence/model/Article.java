package de.hszg.tinyblog.persistence.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * This is the model class for an article.
 * 
 * @author marlene
 * 
 */
@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	@Lob
	private String content;
	@Temporal(TemporalType.DATE)
	private Date publishingDate;
	@OneToMany(orphanRemoval = true)
	private Set<Comment> comments;
	@ManyToOne
	private User user;

	public Article() {

	}

	/**
	 * This constructor is used to create a new article.
	 * 
	 * @param title
	 *            The title of the article.
	 * @param content
	 *            The text of the article.
	 * @param user
	 *            The user who wrote the article.
	 */

	public Article(final String title, final String content, final User user) {

		publishingDate = new Date();
		comments = new HashSet<Comment>();
		this.title = title;
		this.content = content;
		this.user = user;
	}

	/**
	 * This method adds a comment to a Set of Comments.
	 * 
	 * @param comment
	 *            The comment which should be added.
	 */
	public void addComment(final Comment comment) {
		comments.add(comment);
	}

	/**
	 * This method removes a comment from a Set of Comments.
	 * 
	 * @param comment
	 *            The comment which should be removed.
	 */
	public void removeComment(final Comment comment) {
		comments.remove(comment);
	}

	/**
	 * 
	 * @return unmodifiable Set of comments
	 */
	public Set<Comment> getComments() {

		return Collections.unmodifiableSet(comments);

	}

	/**
	 * 
	 * @return the number of entries in an unmodifiable Set of comments
	 */
	public int getNumberOfComments() {
		return Collections.unmodifiableSet(comments).size();
	}

	public Date getPublishingDate() {
		return publishingDate;
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

	public long getId() {
		return id;
	}
}
