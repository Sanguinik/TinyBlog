package de.hszg.model;

import java.util.Date;

/**
 * This is the model class for the comments.
 * @author marlene
 *
 */

public class Comment {

	private String content;
	private Date publishingDate;
	private User user;
	private String name;
	private String email;

	
	/**
	 * This constructor is used for comments created by users that are logged in.
	 * 
	 * @param user The user who wrote the comment.
	 * @param content The text of the comment.
	 */
	public Comment(User user, String content) {
		this(content);
		this.user = user;
	}
	
	/**
	 * This constructor is used for comments created by unregistered users.
	 * 
	 * @param name The name of the user.
	 * @param email The email address of the user.
	 * @param content The text of the comment.
	 */
	public Comment(String name, String email, String content){
		this(content);
		this.name = name;
		this.email = email;
	}
	
	private Comment(String content){
		publishingDate = new Date();
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public User getUser() {
		return user;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public String getContent() {
		return content;
	}

}
