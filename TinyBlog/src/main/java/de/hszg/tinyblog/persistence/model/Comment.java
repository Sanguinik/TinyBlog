package de.hszg.tinyblog.persistence.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This is the model class for the comments.
 * @author marlene
 *
 */

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String content;
	@Temporal(TemporalType.DATE)
	private Date publishingDate;
	private User user;
	private String name;
	private String email;

	
	public Comment(){
		
	}
	
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
	
	/**
	 * Private constructor to avoid code duplication.
	 * Is called with <code>this(content)</code>.
	 * 
	 * @param content 
	 */
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
	
	public long getId(){
		return id;
	}

}
